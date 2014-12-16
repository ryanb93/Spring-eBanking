package web.controller;

import config.Routes;
import core.domain.Account;
import core.domain.AccountType;
import core.domain.Customer;
import core.domain.PostalAddress;
import core.domain.Transaction;
import core.domain.TransactionType;
import core.exceptions.APIException;
import core.services.interfaces.AccountServiceInterface;
import core.services.interfaces.CustomerServiceInterface;
import core.services.interfaces.TransactionServiceInterface;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import web.services.interfaces.UserServiceInterface;

/**
 *
 * This class is for administration control, DO NOT USE IN PRODUCTION.
 *
 * This tool is purely built instead of providing you with a build script. Here
 * you can manage everything without the security limitations of the OAuth API.
 * The Admin controller runs on Backend web interface directly and mimics the
 * power that a real bank would have including creating new customers and web
 * users, adding money to accounts and deleting powers.
 */
@RestController
@Secured("ROLE_ADMIN")
public class AdminController {

    /**
     * The Customer Service, required to call methods on Customers  
     */
    @Autowired
    private CustomerServiceInterface customerService;

    /**
     * The Transaction Service, required to call methods on Transactions  
     */
    @Autowired
    private TransactionServiceInterface transactionService;

    /**
     * The Account Service, required to call methods on Accounts  
     */
    @Autowired
    private AccountServiceInterface accountService;

    /**
     * The APIUser Service, required to call methods on Users  
     */
    @Autowired
    private UserServiceInterface userService;

    /**
     * This method allows for the return of all Customers in the MongoDB as a
     * List so they can by shown in a table on the Admin Panel.
     *
     * @return A List of all the Customers stored in MongoDB
     */
    @RequestMapping(value = Routes.ALL_CUSTOMERS, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Customer> getAllCustomers() throws APIException {
        return customerService.requestAllCustomers();
    }

    /**
     * This method allows for the return of all Customers, Accounts,
     * Transactions, and Users stored in MongoDB as Lists so they can by shown
     * in tables on the Admin Panel.
     *
     * @param model
     * @return a modelandview with Lists of Customers, Accounts, Users, and
     * Transactions.
     */
    @RequestMapping(Routes.ADMIN_PANEL)
    public ModelAndView showAdminPanel(ModelMap model) {
        
        ModelAndView modelAndView = new ModelAndView("adminPanel");
        modelAndView.addObject("customers", customerService.requestAllCustomers());
        modelAndView.addObject("accounts", accountService.requestAllAccounts());
        modelAndView.addObject("transactions", transactionService.requestAllTransactions());
        modelAndView.addObject("users", userService.requestAllUsers());
        return modelAndView;
    }

    /**
     * This method allows us to add a APIUser to MongoDB from the Admin Panel.
     *
     * @param email the APIUser Email Address
     * @param password the APIUser Password
     * @return the AdminPanel ModelAndView via a redirect after saving the new
     * user.
     */
    @RequestMapping(Routes.ADD_USER)
    public ModelAndView addUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        userService.createUser(email, password);
        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     * This method allows us to add a Customer to MongoDB from the Admin Panel.
     *
     * @param firstName the Customer First Name
     * @param lastName the Customer Last Name
     * @param houseNumber the Customer House Number
     * @param street the Customer Street
     * @param city the Customer City
     * @param county the Customer County
     * @param country the Customer Country
     * @param postCode the Customer PostCode
     * @param apiUserId the APIUser ID that we want to link this Customer to.
     * @param builder a Spring Framework component to map Uri Components to
     * String Values
     * @return the AdminPanel ModelAndView via a redirect after saving the new
     * Customer.
     * @throws ParseException when the date of birth is entered in an incorrect
     * format
     * @throws core.exceptions.APIException
     */
    @RequestMapping(value = Routes.ADD_CUSTOMER, method = RequestMethod.POST)
    public ModelAndView createNewCustomer(@RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("houseNumber") String houseNumber,
            @RequestParam("street") String street,
            @RequestParam("city") String city,
            @RequestParam("county") String county,
            @RequestParam("country") String country,
            @RequestParam("postCode") String postCode,
            @RequestParam("apiUserId") String apiUserId,
            UriComponentsBuilder builder) throws ParseException, APIException {
        
        // Build new Customer
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(new PostalAddress(houseNumber, street, city, county, country, postCode));
        customer.setApiUserId(apiUserId);

        customerService.requestNewCustomer(customer);
        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     * This method allows us to remove a Customer from MongoDB from the Admin
     * Panel.
     *
     * @param customerId the ID of the customer we want to remove
     * @param builder a Spring Framework component to map Uri Components to
     * String Values
     * @return the AdminPanel ModelAndView via a redirect after removing the
     * Customer.
     * @throws core.exceptions.APIException
     */
    @RequestMapping(value = Routes.REMOVE_CUSTOMER, method = RequestMethod.POST)
    public ModelAndView removeCustomer(@RequestParam("selectedCustomerId") String customerId, UriComponentsBuilder builder) throws APIException {

        customerService.requestRemoveCustomer(customerId);
        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     * This method allows us to add an Account to MongoDB from the Admin Panel.
     *
     * @param customerId the ID of the Customer we want to link the account to
     * @param selectedAccountType the Type of Account we want to create
     * @param sortCode the Sort Code of the account we want to create
     * @param accountNumber - the Account Number of the Account we want to
     * create
     * @param builder a Spring Framework component to map Uri Components to
     * String Values
     * @return the AdminPanel ModelAndView via a redirect after saving the new
     * Account.
     * @throws core.exceptions.APIException
     */
    @RequestMapping(value = Routes.ADD_ACCOUNT, method = RequestMethod.POST)
    public ModelAndView addAccount(@RequestParam("selectedCustomerId") String customerId,
            @RequestParam("selectedAccountType") String selectedAccountType,
            @RequestParam("sortCode") String sortCode,
            @RequestParam("accountNumber") String accountNumber,
            UriComponentsBuilder builder) throws APIException {

        Account account = new Account();
        // Set Account Type
        AccountType accountType = AccountType.valueOf(selectedAccountType.toUpperCase(Locale.US));
        
        // Build new Account
        account.setAccountType(accountType);
        account.setSortCode(sortCode);
        account.setAccountNumber(accountNumber);
        account.setCustomerId(customerId);

        accountService.requestNewAccount(account);

        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     * This method allows us to remove an Account from MongoDB from the Admin
     * Panel.
     *
     * @param accountId the ID of the Account we want to Remove
     * @param builder a Spring Framework component to map Uri Components to
     * String Values
     * @return the AdminPanel ModelAndView via a redirect after removing the
     * Account.
     * @throws core.exceptions.APIException
     */
    @RequestMapping(value = Routes.REMOVE_ACCOUNT, method = RequestMethod.POST)
    public ModelAndView removeAccount(@RequestParam("selectedAccountId") String accountId, UriComponentsBuilder builder) throws APIException {
        accountService.requestRemoveAccount(accountId);
        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     * This method allows us to add a Transaction to MongoDB from the Admin
     * Panel.
     *
     * @param otherAccountNumber the Account Number of the Recipient in the
     * Transaction
     * @param sending
     * @param otherSortCode the Sort Code of the Recipient in the
     * Transaction
     * @param date the Date of the Transaction
     * @param value the monetary Value of the Transaction
     * @param accountNumber the Account Number that will own this Transaction
     * @param selectedTransactionType the Type of Transaction we want to create
     * @param builder a Spring Framework component to map Uri Components to
     * String Values
     * @return the AdminPanel ModelAndView via a redirect after saving the new
     * Customer.
     * @throws ParseException when the date of the Transaction is entered in an
     * incorrect format
     * @throws core.exceptions.APIException
     */
    @RequestMapping(value = Routes.ADD_TRANSACTION, method = RequestMethod.POST)
    public ModelAndView addTransaction(@RequestParam("accountNumber") String accountNumber,
            @RequestParam("otherAccountNumber") String otherAccountNumber,
            @RequestParam("otherSortCode") String otherSortCode,
            @RequestParam("sending") String sending,
            @RequestParam("value") String value,
            @RequestParam("selectedTransactionType") String selectedTransactionType,
            UriComponentsBuilder builder) throws ParseException, APIException {
        
        Transaction transaction = new Transaction();
        
        // Set the Transaction Date
        transaction.setDate(new Date());
        
        //Set the Transaction Value
        Double transactionValue = Double.parseDouble(value);
        
        //Set the Transaction type.
        TransactionType type = TransactionType.fromString(selectedTransactionType);
        transaction.setTransactionType(type);
        
        boolean boolSending = Boolean.parseBoolean(sending);
        transaction.setSending(boolSending);
        
        // Build new Transaction
        transaction.setAccountNumber(accountNumber);
        transaction.setOtherAccountNumber(otherAccountNumber);
        transaction.setOtherSortCode(otherSortCode);
        transaction.setValue(transactionValue);
        
        
        transactionService.requestNewTransaction(transaction);

        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     * This method allows us to remove aa Transaction from MongoDB from the
     * Admin Panel.
     *
     * @param transactionId the ID of the Transaction we want to Remove
     * @param builder a Spring Framework component to map Uri Components to
     * String Values
     * @return the AdminPanel ModelAndView via a redirect after removing the
     * Account.
     * @throws core.exceptions.APIException
     */
    @RequestMapping(value = Routes.REMOVE_TRANSACTION, method = RequestMethod.POST)
    public ModelAndView removeTransaction(@RequestParam("selectedTransactionId") String transactionId, UriComponentsBuilder builder) throws APIException {
        transactionService.requestRemoveTransaction(transactionId);
        return new ModelAndView("redirect:/adminPanel");
    }

}

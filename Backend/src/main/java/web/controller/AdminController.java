package web.controller;

import core.domain.Account;
import core.domain.AccountType;
import core.domain.Customer;
import core.domain.PostalAddress;
import core.domain.Transaction;
import core.domain.TransactionType;
import core.repository.AccountRepository;
import core.repository.CustomerRepository;
import core.repository.TransactionRepository;
import core.services.interfaces.CustomerServiceInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import config.Routes;
import core.services.AccountService;
import core.services.interfaces.TransactionServiceInterface;
import java.util.Locale;
import web.domain.ApiUser;
import web.repository.UserRepository;
import web.services.interfaces.UserServiceInterface;

/**
 * 
 * This class is for administration control, DO NOT USE IN PRODUCTION.
 *
 * This tool is purely built instead of providing you with a build script.
 * Here you can manage everything without the security limitations of the
 * OAuth API. The Admin controller runs on Backend web interface directly
 * and mimics the power that a real bank would have including creating new
 * customers and web users, adding money to accounts and deleting powers.
 */
@RestController
public class AdminController {

    @Autowired
    private CustomerServiceInterface customerService;

    @Autowired
    private TransactionServiceInterface transactionService;
    
    @Autowired
    private AccountService accountService;

    @Autowired
    private UserServiceInterface userService;

    /**
     * This method allows for the return of all Customers in the MongoDB
     * as a List so they can by shown in a table on the Admin Panel.
     *
     * @return A List of all the Customers stored in MongoDB
     */
    @RequestMapping(value = Routes.ALL_CUSTOMERS, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return customerService.requestAllCustomers();
    }

    /**
     *This method allows for the return of all Customers, Accounts, Transactions,
     * and Users stored in MongoDB as Lists so they can by shown in tables on the Admin Panel.
     * 
     * @param model
     * @return a modelandview with Lists of Customers, Accounts, Users, and Transactions.
     */
    @RequestMapping(Routes.ADMIN_PANEL)
    public ModelAndView showAdminPanel(ModelMap model) {
        ModelAndView modelAndView = new ModelAndView("adminPanel");
        modelAndView.addObject("customers", customerService.fetchAllMongoDbCustomers());
        modelAndView.addObject("accounts", accountService.fetchAllMongoDbAccounts());
        modelAndView.addObject("transactions", transactionService.fetchAllMongoDbTransactions());
        modelAndView.addObject("users", userService.fetchAllMongoDbUsers());
        return modelAndView;
    }

    /**
     *This method allows us to add a User to MongoDB from the Admin Panel.
     * 
     * @param email the User Email Address
     * @param password the User Password
     * @return the AdminPanel ModelAndView via a redirect after saving the new user.
     */
    @RequestMapping(Routes.ADD_USER)
    public ModelAndView addUser(@RequestParam("email") String email, @RequestParam("password") String password) {
        ApiUser user = new ApiUser();
        user.setEmailAddress(email);
        ApiUser createdUser = userService.createUser(user, password);
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
     * @param builder a Spring Framework component to map Uri Components to String Values
     * @return the AdminPanel ModelAndView via a redirect after saving the new Customer.
     * @throws ParseException when the date of birth is entered in an incorrect format
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
            UriComponentsBuilder builder) throws ParseException {

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAddress(new PostalAddress(houseNumber, street, city, county, country, postCode));
        customer.setApiUserId(apiUserId);
        
        customerService.requestNewCustomer(customer);
        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     *This method allows us to remove a Customer from MongoDB from the Admin Panel.
     * 
     * @param customerId the ID of the customer we want to remove
     * @param builder a Spring Framework component to map Uri Components to String Values
     * @return the AdminPanel ModelAndView via a redirect after removing the Customer.
     */
    @RequestMapping(value = Routes.REMOVE_CUSTOMER, method = RequestMethod.POST)
    public ModelAndView removeCustomer(@RequestParam("selectedCustomerId") String customerId, UriComponentsBuilder builder) {

        customerService.removeCustomer(customerId);
        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     *This method allows us to add an Account to MongoDB from the Admin Panel.
     * 
     * @param customerId the ID of the Customer we want to link the account to
     * @param selectedAccountType the Type of Account we want to create
     * @param sortCode the Sort Code of the account we want to create
     * @param accountNumber - the Account Number of the Account we want to create
     * @param builder a Spring Framework component to map Uri Components to String Values
     * @return the AdminPanel ModelAndView via a redirect after saving the new Account.
     */
    @RequestMapping(value = Routes.ADD_ACCOUNT, method = RequestMethod.POST)
    public ModelAndView addAccount(@RequestParam("selectedCustomerId") String customerId,
            @RequestParam("selectedAccountType") String selectedAccountType,
            @RequestParam("sortCode") String sortCode,
            @RequestParam("accountNumber") String accountNumber,
            UriComponentsBuilder builder) {

        Account account = new Account();

        AccountType accountType = AccountType.valueOf(selectedAccountType.toUpperCase(Locale.US));

        account.setAccountType(accountType);
        account.setSortCode(sortCode);
        account.setAccountNumber(accountNumber);
        account.setCustomerId(customerId);

        accountService.requestNewAccount(account);

        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     * This method allows us to remove an Account from MongoDB from the Admin Panel.
     *
     * @param accountId the ID of the Account we want to Remove
     * @param builder a Spring Framework component to map Uri Components to String Values
     * @return the AdminPanel ModelAndView via a redirect after removing the Account.
     */
    @RequestMapping(value = Routes.REMOVE_ACCOUNT, method = RequestMethod.POST)
    public ModelAndView removeAccount(@RequestParam("selectedAccountId") String accountId, UriComponentsBuilder builder) {

        accountService.removeAccount(accountId);
        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     * This method allows us to add a Transaction to MongoDB from the Admin Panel.
     *
     * @param senderAccountNumber the Account Number of the Sender in the Transaction
     * @param senderSortCode the Sort Code of the Sender in the Transaction
     * @param recipientAccountNumber the Account Number of the Recipient in the Transaction
     * @param recipientSortCode the Sort Code of the Recipient in the Transaction
     * @param date the Date of the Transaction
     * @param value the monetary Value of the Transaction
     * @param accountNumber the Account Number that will own this Transaction
     * @param selectedTransactionType the Type of Transaction we want to create
     * @param builder a Spring Framework component to map Uri Components to String Values
     * @return the AdminPanel ModelAndView via a redirect after saving the new Customer.
     * @throws ParseException when the date of the Transaction is entered in an incorrect format
     */
    @RequestMapping(value = Routes.ADD_TRANSACTION, method = RequestMethod.POST)
    public ModelAndView addTransaction(@RequestParam("senderAccountNumber") String senderAccountNumber,
            @RequestParam("senderSortCode") String senderSortCode,
            @RequestParam("recipientAccountNumber") String recipientAccountNumber,
            @RequestParam("recipientSortCode") String recipientSortCode,
            @RequestParam("date") String date,
            @RequestParam("value") String value,
            @RequestParam("accountNumber") String accountNumber,
            @RequestParam("selectedTransactionType") String selectedTransactionType,
            UriComponentsBuilder builder) throws ParseException {

        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date transactionDate = formatter.parse(date);
        Double transactionValue = Double.parseDouble(value);
        Transaction transaction = new Transaction();

        switch (selectedTransactionType) {
            case "cash":
                transaction.setTransactionType(TransactionType.CASH);
                break;
            case "creditCard":
                transaction.setTransactionType(TransactionType.CREDIT_CARD);
                break;
            case "debitCard":
                transaction.setTransactionType(TransactionType.DEBIT_CARD);
                break;
            case "directDebit":
                transaction.setTransactionType(TransactionType.DIRECT_DEBIT);
                break;
            case "bacs":
                transaction.setTransactionType(TransactionType.BACS);
                break;
            case "standingOrder":
                transaction.setTransactionType(TransactionType.STANDING_ORDER);
                break;
            case "paypal":
                transaction.setTransactionType(TransactionType.PAYPAL);
                break;
            case "other":
                transaction.setTransactionType(TransactionType.OTHER);
                break;
        }

        transaction.setDate(transactionDate);

        transaction.setRecipientAccountNumber(recipientAccountNumber);
        transaction.setRecipientSortCode(recipientSortCode);
        transaction.setSenderAccountNumber(senderAccountNumber);
        transaction.setSenderSortCode(senderSortCode);

        transaction.setValue(transactionValue);
        transaction.setAccountNumber(accountNumber);
        transactionService.requestNewTransaction(transaction);

        return new ModelAndView("redirect:/adminPanel");
    }

    /**
     * This method allows us to remove aa Transaction from MongoDB from the Admin Panel.
     *
     * @param transactionId the ID of the Transaction we want to Remove
     * @param builder a Spring Framework component to map Uri Components to String Values
     * @return the AdminPanel ModelAndView via a redirect after removing the Account.
     */
    @RequestMapping(value = Routes.REMOVE_TRANSACTION, method = RequestMethod.POST)
    public ModelAndView removeTransaction(@RequestParam("selectedTransactionId") String transactionId, UriComponentsBuilder builder) {

        transactionService.removeTransaction(transactionId);
        return new ModelAndView("redirect:/adminPanel");
    }

}

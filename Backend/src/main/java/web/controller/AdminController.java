package web.controller;

import core.domain.Account;
import core.domain.AccountType;
import core.domain.Customer;
import core.domain.PostalAddress;
import core.domain.Transaction;
import core.domain.TransactionType;
import core.events.customers.AllCustomersEvent;
import core.events.customers.RequestAllCustomersEvent;
import core.repository.AccountRepository;
import core.repository.CustomerRepository;
import core.repository.TransactionRepository;
import core.services.AccountService;
import core.services.CustomerService;
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
import core.events.transactions.RequestCreateTransactionEvent;
import core.services.TransactionService;
import java.util.Locale;
import web.domain.ApiUser;
import web.events.users.CreateUserRequest;
import web.repository.UserRepository;
import web.services.UserService;


/**
 * TODO: REMOVE AFTER TESTING
 * 
 * This class is for administration control. 
 * 
 * It should be the only controller that can create/delete new customers.
 * We keep this out of the Customers controller because that is for public
 * facing operations on individual Customers.
 */
@RestController
public class AdminController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private TransactionService transactionService;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserService userService;

    /**
     * 
     * @return 
     */
    @RequestMapping(value = Routes.ALL_CUSTOMERS, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        AllCustomersEvent event = customerService.requestAllCustomers(new RequestAllCustomersEvent());
        return event.getCustomerDetails();
    }
    
    /**
     * 
     * @param model
     * @return 
     */
    @RequestMapping(Routes.ADMIN_PANEL)
    public ModelAndView showAdminPanel( ModelMap model ) {
        ModelAndView modelAndView = new ModelAndView("adminPanel");
        modelAndView.addObject("customers", customerRepository.findAll());
        modelAndView.addObject("accounts", accountRepository.findAll());
        modelAndView.addObject("transactions", transactionRepository.findAll());
        modelAndView.addObject("users", userRepository.findAll());
	return modelAndView;
    }
    
    /**
     * 
     * @param email
     * @param password
     * @return 
     */
    @RequestMapping(Routes.ADD_USER)
    public ModelAndView addUser(@RequestParam("email")String email, @RequestParam("password")String password) {
        CreateUserRequest userRequest = new CreateUserRequest();
        ApiUser user = new ApiUser();
        user.setEmailAddress(email);
        userRequest.setPassword(password);
        userRequest.setUser(user);
        ApiUser createdUser = userService.createUser(userRequest);
        
        return new ModelAndView("redirect:/adminPanel");
    }
    
    /**
     * 
     * @param firstName
     * @param lastName
     * @param dateOfBirth
     * @param houseNumber
     * @param street
     * @param city
     * @param county
     * @param country
     * @param postCode
     * @param apiUserId
     * @param builder
     * @return
     * @throws ParseException 
     */
    @RequestMapping(value = Routes.ADD_CUSTOMER, method = RequestMethod.POST)
    public ModelAndView createNewCustomer(@RequestParam("firstName") String firstName,
                                          @RequestParam("lastName") String lastName,
                                          @RequestParam("dateOfBirth") String dateOfBirth,
                                          @RequestParam("houseNumber") String houseNumber, 
                                          @RequestParam("street") String street,
                                          @RequestParam("city") String city, 
                                          @RequestParam("county") String county,
                                          @RequestParam("country") String country, 
                                          @RequestParam("postCode") String postCode,
                                          @RequestParam("apiUserId") String apiUserId, 
                                          UriComponentsBuilder builder) throws ParseException {
        
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date birth = formatter.parse(dateOfBirth);
        
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setDateOfBirth(birth);
        customer.setAddress(new PostalAddress(houseNumber, street, city, county, country, postCode));
        customer.setApiUserId(apiUserId);

        customerRepository.save(customer);
        return new ModelAndView("redirect:/adminPanel");
    }
    
    /**
     * 
     * @param customerId
     * @param builder
     * @return 
     */
    @RequestMapping(value = Routes.REMOVE_CUSTOMER, method = RequestMethod.POST)
    public ModelAndView removeCustomer(@RequestParam("selectedCustomerId") String customerId, UriComponentsBuilder builder){
        //TODO: This should be using the customerService. Not deleting directly.
        Customer customerToDelete =  customerRepository.findOne(customerId);
        customerRepository.delete(customerToDelete);
        return new ModelAndView("redirect:/adminPanel");
    }
    
    /**
     * 
     * @param customerId
     * @param selectedAccountType
     * @param sortCode
     * @param accountNumber
     * @param builder
     * @return 
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
        
        //TODO: This should be passed to the accountService.
        accountRepository.save(account);
        
        return new ModelAndView("redirect:/adminPanel");
    }
    
    /**
     * 
     * @param accountId
     * @param builder
     * @return 
     */
    @RequestMapping(value = Routes.REMOVE_ACCOUNT, method = RequestMethod.POST)
    public ModelAndView removeAccount(@RequestParam("selectedAccountId") String accountId, UriComponentsBuilder builder){
        //TODO: This should be passed to the accountService.
        Account accountToDelete =  accountRepository.findOne(accountId);
        accountRepository.delete(accountToDelete);
        return new ModelAndView("redirect:/adminPanel");
    }
    
    /**
     * 
     * @param senderAccountNumber
     * @param senderSortCode
     * @param recipientAccountNumber
     * @param recipientSortCode
     * @param date
     * @param value
     * @param accountNumber
     * @param selectedTransactionType
     * @param builder
     * @return
     * @throws ParseException 
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
        
        switch (selectedTransactionType){
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
        RequestCreateTransactionEvent event = new RequestCreateTransactionEvent(transaction);
        transactionService.requestNewTransaction(event);
        
        return new ModelAndView("redirect:/adminPanel");
    }
    
    @RequestMapping(value = Routes.REMOVE_TRANSACTION, method = RequestMethod.POST)
    public ModelAndView removeTransaction(@RequestParam("selectedTransactionId") String transactionId, UriComponentsBuilder builder){
     Transaction transactionToDelete =  transactionRepository.findOne(transactionId);
     transactionRepository.delete(transactionToDelete);
     
      return new ModelAndView("redirect:/adminPanel");
    }
    
}

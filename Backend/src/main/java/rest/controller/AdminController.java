package rest.controller;

import core.domain.Account;
import core.domain.Customer;
import core.domain.PostalAddress;
import core.events.accounts.CreateAccountEvent;
import core.events.accounts.RequestNewAccountEvent;
import core.events.customers.AllCustomersEvent;
import core.events.customers.CreateCustomerEvent;
import core.events.customers.RequestAllCustomersEvent;
import core.events.customers.RequestNewCustomerEvent;
import core.repository.CustomerRepository;
import core.services.AccountService;
import core.services.CustomerService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;
import rest.config.Routes;

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
    private CustomerRepository customerRepository;
    

    @RequestMapping(value = Routes.API, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        AllCustomersEvent event = customerService.requestAllCustomers(new RequestAllCustomersEvent());
        return event.getCustomerDetails();
    }
    
    @RequestMapping(Routes.ADMIN_PANEL)
    public ModelAndView helloWorld( ModelMap model ) {
        List customers = customerRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("adminPanel");
        modelAndView.addObject("customers", customers);
		return modelAndView;
	}
    
    
        @RequestMapping(value = Routes.ADMIN_PANEL+"/addCustomer", method = RequestMethod.POST)
    public ModelAndView createNewCustomer(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
                                                       @RequestParam("dateOfBirth") String dateOfBirth, @RequestParam("houseNumber") String houseNumber, 
                                                       @RequestParam("street") String street, @RequestParam("city") String city, 
                                                       @RequestParam("county") String county, @RequestParam("country") String country, 
                                                       @RequestParam("postCode") String postCode, UriComponentsBuilder builder) throws ParseException {
        
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
        Date birth = formatter.parse(dateOfBirth);
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setDateOfBirth(birth);
        customer.setAddress(new PostalAddress(houseNumber, street, city, county, country, postCode));
        
        CreateCustomerEvent event = customerService.requestNewCustomer(new RequestNewCustomerEvent(customer));

        Customer newCustomer = event.getCustomer();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(Routes.API)
                .buildAndExpand(newCustomer.getCustomerId()).toUri());

        return new ModelAndView("redirect:/adminPanel");
    }
    
    @RequestMapping(value = Routes.ADMIN_PANEL+"/removeCustomer", method = RequestMethod.POST)
    public ModelAndView RemoveCustomer(@RequestParam("selectedCustomerId") String customerId, UriComponentsBuilder builder){
     Customer customerToDelete =  customerRepository.findOne(customerId);
     customerRepository.delete(customerToDelete);
      return new ModelAndView("redirect:/adminPanel");
    }
    
            /**@RequestMapping(value = Routes.API+"/addCustomer", method = RequestMethod.POST)
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer, UriComponentsBuilder builder) {

        CreateCustomerEvent event = customerService.requestNewCustomer(new RequestNewCustomerEvent(customer));

        Customer newCustomer = event.getCustomer();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(Routes.API)
                .buildAndExpand(newCustomer.getCustomerId()).toUri());

        return new ResponseEntity(newCustomer, headers, HttpStatus.CREATED);
    }**/
    
    @RequestMapping(value = Routes.ACCOUNTS, method = RequestMethod.POST)
    public ResponseEntity<Account> createNewAccount(@PathVariable("customer_id") String customerId, @RequestBody Account account, UriComponentsBuilder builder) {

        account.setCustomerId(customerId);

        CreateAccountEvent event = accountService.requestNewAccount(new RequestNewAccountEvent(account));

        Account newAccount = event.getAccount();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(Routes.API)
                .buildAndExpand(newAccount.getAccountId()).toUri());

        return new ResponseEntity(newAccount, headers, HttpStatus.CREATED);
    } 
    
    //TODO: Remove after testing has been complete
    @RequestMapping(Routes.TEST_JSON_CUSTOMER)
    public Customer getOne() {
        Customer cust = new Customer("Jim", "Beanz", new Date(1990, 5, 19),
                new PostalAddress(
                        "123",
                        "Manor Park Student Village",
                        "Guildford",
                        "Surrey",
                        "United Kingdom",
                        "GU2 7YW"));
        return cust;
    }

    
}

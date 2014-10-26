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
import core.services.AccountService;
import core.services.CustomerService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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

    @RequestMapping(value = Routes.API, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        AllCustomersEvent event = customerService.requestAllCustomers(new RequestAllCustomersEvent());
        return event.getCustomerDetails();
    }

    @RequestMapping(value = Routes.API, method = RequestMethod.POST)
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer, UriComponentsBuilder builder) {

        CreateCustomerEvent event = customerService.requestNewCustomer(new RequestNewCustomerEvent(customer));

        Customer newCustomer = event.getCustomer();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path(Routes.API)
                .buildAndExpand(newCustomer.getCustomerId()).toUri());

        return new ResponseEntity(newCustomer, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(value = Routes.ACCOUNTS, method = RequestMethod.POST)
    public ResponseEntity<Account> createNewAccount(@RequestBody Account account, UriComponentsBuilder builder) {

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

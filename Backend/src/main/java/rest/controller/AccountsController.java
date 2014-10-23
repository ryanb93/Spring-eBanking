package rest.controller;

import core.domain.Account;
import core.domain.AccountType;
import core.domain.Customer;
import core.domain.PostalAddress;
import core.services.AccountService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import rest.config.Routes;

@RestController
@RequestMapping(Routes.ACCOUNTS)
public class AccountsController {
    
    //@Autowired
    private AccountService accountService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Account> getAllAccounts() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = Routes.CUSTOMER_ACCOUNT, method = RequestMethod.GET)
    public Account getCustomerAccount(@PathVariable String id) {
        return new Account("12345678","223456",AccountType.CURRENT);
    }
    
    //TODO: Remove after testing has been complete
    @RequestMapping("/jsonAccount")
    public Customer getOne() {
        Customer cust = new Customer("Jim", "Beanz", new Date(1990, 5, 19),
                                new PostalAddress(
                                        "123",
                                        "121211112",
                                        "sdfvdsg",
                                        "dsfss",
                                        "",
                                        "sdsf"));
        return cust;
    }
    
}

package rest.controller;

import core.domain.Account;
import core.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import rest.config.Routes;

@RestController
@RequestMapping(Routes.ACCOUNTS)
public class AccountsController {
    
    //@Autowired
    private AccountService accountService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Account> getAllAccounts() {
        return new ResponseEntity<Account>(HttpStatus.OK);
    }
    
}

package rest.controller;

import core.domain.Account;
import core.events.accounts.AccountDetailsEvent;
import core.events.accounts.AllAccountsEvent;
import core.events.accounts.RequestAccountDetailsEvent;
import core.events.accounts.RequestAllAccountsEvent;
import core.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import config.Routes;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestHeader;
import web.domain.ApiUser;

@RestController
@RequestMapping(Routes.ACCOUNTS)
@Secured("ROLE_USER")
public class AccountsController {

    @Autowired
    private AccountService accountService;
    
    /**
     * When a user makes a GET request to this URL we want to return the 
     * details of a single customer account.
     * 
     * @return The Account details as JSON.
     */
    @RequestMapping(value = Routes.CUSTOMER_ACCOUNT, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Account getCustomerAccount(@AuthenticationPrincipal ApiUser user) {
                      
        RequestAccountDetailsEvent request = new RequestAccountDetailsEvent(user.getId());
        AccountDetailsEvent event = accountService.requestAccountDetails(request);
        return event.getAccount();
    }
}

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
import rest.config.Routes;

@RestController
@RequestMapping(Routes.ACCOUNTS)
@Secured("ROLE_USER")
public class AccountsController {

    @Autowired
    private AccountService accountService;

    /**
     * When a user makes a GET request to this URL we want
     * to return a list of all their accounts.
     * 
     * @param customerId - The ID of the customer to get accounts for.
     * 
     * @return All of this user's accounts.
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public AllAccountsEvent getAllAccounts(@PathVariable("customer_id") String customerId) {
        RequestAllAccountsEvent request = new RequestAllAccountsEvent(customerId);
        AllAccountsEvent event = accountService.requestAllAccounts(request);
        return event;
    }

    /**
     * When a user makes a GET request to this URL we want to return the 
     * details of a single customer account.
     * 
     * @param id - The ID of the account to access.
     * @return The Account details as JSON.
     */
    @RequestMapping(value = Routes.CUSTOMER_ACCOUNT, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Account getCustomerAccount(@PathVariable("account_id") String id) {
        RequestAccountDetailsEvent request = new RequestAccountDetailsEvent(id);
        AccountDetailsEvent event = accountService.requestAccountDetails(request);
        return event.getAccount();
    }
}

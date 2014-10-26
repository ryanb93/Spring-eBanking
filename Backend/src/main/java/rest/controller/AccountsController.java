package rest.controller;

import core.domain.Account;
import core.domain.AccountType;
import core.events.accounts.AllAccountsEvent;
import core.events.accounts.RequestAllAccountsEvent;
import core.services.AccountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rest.config.Routes;

@RestController
@RequestMapping(Routes.ACCOUNTS)
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
    public List<Account> getAllAccounts(@PathVariable("customer_id") String customerId) {
        RequestAllAccountsEvent request = new RequestAllAccountsEvent(customerId);
        AllAccountsEvent event = accountService.requestAllAccounts(request);
        return event.getAccounts();
    }

    @RequestMapping(value = Routes.CUSTOMER_ACCOUNT, method = RequestMethod.GET)
    public Account getCustomerAccount(@PathVariable("account_id") String id) {
        return new Account("12345678", "223456", AccountType.CURRENT);
    }
}

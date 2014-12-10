package rest.controller;

import components.AuthHelper;
import core.domain.Account;
import core.events.accounts.AccountDetailsEvent;
import core.events.accounts.AllAccountsEvent;
import core.events.accounts.RequestAllAccountsEvent;
import core.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import config.Routes;
import core.events.accounts.RequestAccountDetailsFromNumberEvent;
import core.services.CustomerService;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(Routes.ACCOUNTS)
@Secured("ROLE_USER")
public class AccountsController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerService customerService;

    /**
     * When a user makes a GET request to this URL we want to return a list of
     * all their accounts.
     *
     * @param auth
     *
     * @return All of this user's accounts.
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAllAccounts(@AuthenticationPrincipal OAuth2Authentication auth) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        List<Account> accounts = null;

        if (AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            String customerId = AuthHelper.ID_FROM_AUTH(customerService, auth);

            if (customerId == null) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                RequestAllAccountsEvent request = new RequestAllAccountsEvent(customerId);
                AllAccountsEvent event = accountService.requestAllAccounts(request);
                accounts = event.getAccounts();
            }
        } else {
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity(accounts, headers, status);

    }

    /**
     * When a user makes a GET request to this URL we want to return the details
     * of a single customer account.
     *
     * @param auth
     * @param accountNumber
     * @return The Account details as JSON.
     */
    @RequestMapping(value = Routes.SINGLE_ACCOUNT, method = RequestMethod.GET)
    public ResponseEntity<Account> getCustomerAccount(@AuthenticationPrincipal OAuth2Authentication auth,
            @PathVariable("account_number") String accountNumber) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Account account = null;

        if (AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            String customerId = AuthHelper.ID_FROM_AUTH(customerService, auth);
            if (customerId == null) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                RequestAccountDetailsFromNumberEvent request = new RequestAccountDetailsFromNumberEvent(accountNumber);
                AccountDetailsEvent event = accountService.requestAccountDetailsFromNumber(request);
                account = event.getAccount();
                if (account == null) {
                    status = HttpStatus.BAD_REQUEST;
                } else if (!account.getCustomerId().equals(customerId)) {
                    account = null;
                    status = HttpStatus.BAD_REQUEST;
                }
            }
        } else {
            status = HttpStatus.UNAUTHORIZED;
        }

        return new ResponseEntity(account, headers, status);
    }
}

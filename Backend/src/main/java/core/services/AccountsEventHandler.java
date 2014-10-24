/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.services;

import core.events.accounts.AllAccountsEvent;
import core.events.accounts.RequestAllAccountsEvent;

/**
 *
 * @author Ryan
 */
public class AccountsEventHandler implements AccountService {
    
    @Override
    public AllAccountsEvent requestAllAccounts(RequestAllAccountsEvent requestAllAccountsEvent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

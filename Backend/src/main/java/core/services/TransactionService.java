/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.services;

import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.RequestAllTransactionsEvent;

/**
 *
 * @author Ben
 */
public interface TransactionService {
    
    public AllTransactionsEvent requestAllTransactions(RequestAllTransactionsEvent requestAllTransactionsEvent);
}

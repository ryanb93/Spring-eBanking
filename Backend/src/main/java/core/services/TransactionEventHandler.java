/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.services;

import core.events.transactions.AllTransactionsEvent;
import core.events.transactions.RequestAllTransactionsEvent;
import core.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ben
 */
public class TransactionEventHandler implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public AllTransactionsEvent requestAllTransactions(RequestAllTransactionsEvent requestAllTransactionsEvent) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.repository;

import core.domain.Account;
import core.domain.Transaction;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Ben
 */
public interface TransactionRepository {
    
    Transaction save(Transaction order);

    void delete(UUID key);

    Transaction findById(UUID key);

    List<Transaction> findAllForAccount(Account order);
    
}

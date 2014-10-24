/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.events.customers;

import core.domain.Customer;
import core.events.ReadEvent;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Ryan
 */
public class AllCustomersEvent extends ReadEvent {
    
  private final List<Customer> customers;

  public AllCustomersEvent(List<Customer> customers) {
    this.customers = Collections.unmodifiableList(customers);
  }

  public List<Customer> getCustomerDetails() {
    return this.customers;
  }
    
    
}

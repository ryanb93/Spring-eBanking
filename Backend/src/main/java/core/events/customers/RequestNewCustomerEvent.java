/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.events.customers;

import core.domain.Customer;

public class RequestNewCustomerEvent {  
    
      private final Customer customer;

  public RequestNewCustomerEvent(final Customer customer) {
    this.customer = customer;
  }
  
  public Customer getCustomer() {
      return this.customer;
  }

}


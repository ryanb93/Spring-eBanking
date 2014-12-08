package core.events.customers;

import core.domain.Customer;

public class CreateCustomerEvent {
    
  private final Customer customer;

  public CreateCustomerEvent(final Customer customer) {
    this.customer = customer;
  }
  
  public Customer getCustomer() {
      return this.customer;
  }

  
}

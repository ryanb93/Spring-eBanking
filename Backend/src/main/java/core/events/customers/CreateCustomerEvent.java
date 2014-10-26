package core.events.customers;

import core.domain.Customer;
import core.events.CreateEvent;

public class CreateCustomerEvent extends CreateEvent {
    
  private final Customer customer;

  public CreateCustomerEvent(final Customer customer) {
    this.customer = customer;
  }
  
  public Customer getCustomer() {
      return this.customer;
  }

  
}

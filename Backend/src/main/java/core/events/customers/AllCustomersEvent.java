package core.events.customers;

import core.domain.Customer;
import core.events.ReadEvent;
import java.util.Collections;
import java.util.List;

public class AllCustomersEvent extends ReadEvent {
    
  private final List<Customer> customers;

  public AllCustomersEvent(List<Customer> customers) {
    this.customers = Collections.unmodifiableList(customers);
  }

  public List<Customer> getCustomerDetails() {
    return this.customers;
  }
    
    
}

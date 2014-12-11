package core.services.interfaces;

import core.domain.Customer;
import java.util.List;

public interface CustomerServiceInterface {
    public Customer requestCustomerDetails(String customerId);
    public List<Customer> requestAllCustomers();
    public Customer requestNewCustomer(Customer customer);
    public Customer requestUpdateCustomer(Customer customer);
    public String requestCustomerId(String apiUserId);
    public void removeCustomer(String customerId);
}

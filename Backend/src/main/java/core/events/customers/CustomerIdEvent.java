package core.events.customers;

public class CustomerIdEvent {

    private String customerId;

    public CustomerIdEvent(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

}

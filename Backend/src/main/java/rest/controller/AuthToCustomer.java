package rest.controller;

import core.events.customers.CustomerIdEvent;
import core.events.customers.RequestCustomerIdEvent;
import core.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import web.domain.User;


public class AuthToCustomer {
    
    public static String ID_FROM_AUTH(CustomerService customerService, OAuth2Authentication auth) {        
        String customerId = null;
        User user = (User)auth.getPrincipal();
        RequestCustomerIdEvent requestCustomerIdEvent = new RequestCustomerIdEvent(user.getId());
        CustomerIdEvent customerIdEvent = customerService.requestCustomerId(requestCustomerIdEvent);
        if(customerIdEvent != null) customerId = customerIdEvent.getCustomerId();
        return customerId;
    }
    
}

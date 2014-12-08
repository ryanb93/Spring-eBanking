package rest.controller;

import core.domain.Customer;
import core.events.customers.CustomerDetailsEvent;
import core.events.customers.RequestCustomerDetailsEvent;
import core.events.customers.RequestUpdateCustomerDetailsEvent;
import core.events.customers.UpdateCustomerDetailsEvent;
import core.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import config.Routes;
import core.events.customers.CustomerIdEvent;
import core.events.customers.RequestCustomerIdEvent;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import web.domain.User;

@RestController
@RequestMapping(Routes.API)
@Secured("ROLE_USER")
public class CustomersController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Customer> getSingleCustomerDetails(@AuthenticationPrincipal OAuth2Authentication auth) {
        
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Customer customer = null;

        User user = (User)auth.getPrincipal();
        
        RequestCustomerIdEvent requestCustomerIdEvent = new RequestCustomerIdEvent(user.getId());
        CustomerIdEvent customerIdEvent = customerService.requestCustomerId(requestCustomerIdEvent);
        
        
        if(customerIdEvent == null) {
            status = HttpStatus.BAD_REQUEST;
        }
        else {
            String customerId = customerIdEvent.getCustomerId();
            RequestCustomerDetailsEvent request = new RequestCustomerDetailsEvent(customerId);
            CustomerDetailsEvent event = customerService.requestCustomerDetails(request);
            customer = event.getCustomer();
        }
        return new ResponseEntity(customer, headers, status);

    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> updateCustomerDetails(@RequestBody Customer customer, UriComponentsBuilder builder) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        Customer newCustomer = null;

        if(!this.isValid(customer)) {
            status = HttpStatus.BAD_REQUEST;
        }
        else {
            UpdateCustomerDetailsEvent event = customerService.requestUpdateCustomer(new RequestUpdateCustomerDetailsEvent(customer));
            if(event != null) {
                newCustomer = event.getUpdatedCustomer();
                headers.setLocation(builder.path(Routes.API).buildAndExpand(newCustomer.getCustomerId()).toUri());
                status = HttpStatus.CREATED;
            }
        }
 
        return new ResponseEntity(newCustomer, headers, status);
    }
    
    private boolean isValid(Customer customer) {
        boolean valid = true;
        if(customer.getCustomerId() == null) valid = false;
        else if(customer.getFirstName() == null) valid = false;
        else if(customer.getLastName() == null) valid = false;
        else if(customer.getDateOfBirth() == null) valid = false;
        else if(customer.getAddress() == null) valid = false;
        return valid;
    }
    
}

package rest.controller;

import core.domain.Customer;
import core.events.customers.CustomerDetailsEvent;
import core.events.customers.RequestCustomerDetailsEvent;
import core.services.CustomerEventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import rest.config.Routes;

@RestController
@RequestMapping(Routes.CUSTOMER)
public class CustomersController {

    @Autowired
    private CustomerEventHandler customerService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Customer getSingleCustomerDetails(@PathVariable("customer_id") String customerId) {
        RequestCustomerDetailsEvent request = new RequestCustomerDetailsEvent(customerId);
        CustomerDetailsEvent event = customerService.requestCustomerDetails(request);
        return event.getCustomer();
    }
    
}

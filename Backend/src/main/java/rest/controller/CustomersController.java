/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest.controller;

import core.domain.Customer;
import core.events.customers.AllCustomersEvent;
import core.events.customers.CreateCustomerEvent;
import core.events.customers.RequestAllCustomersEvent;
import core.events.customers.RequestNewCustomerEvent;
import core.services.CustomerEventHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import rest.config.Routes;

@RestController
@RequestMapping(Routes.DETAILS)
public class CustomersController {
    
    @Autowired
    private CustomerEventHandler customerService;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        AllCustomersEvent event = customerService.requestAllCustomers(new RequestAllCustomersEvent());
        return event.getCustomerDetails();
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> createOrder(@RequestBody Customer customer, UriComponentsBuilder builder) {

        CreateCustomerEvent event = customerService.requestNewCustomer(new RequestNewCustomerEvent(customer));

        Customer newCustomer = event.getCustomer();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(
                builder.path("/api/customers/{id}")
                        .buildAndExpand(newCustomer.getCustomerId()).toUri());

        return new ResponseEntity(newCustomer, headers, HttpStatus.CREATED);
    }
    
}

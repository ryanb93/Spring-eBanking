package rest.controller;

import components.AuthHelper;
import config.Routes;
import core.domain.Customer;
import core.exceptions.APIException;
import core.services.interfaces.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Unlike traditional Controllers in an MVC architecture this is a RESTful
 * controller. 
 * This means that it does not have a UI associated with it.
 * The purpose of this controller is to manage customers on the web application.
 * 
 * When requests are made to the application from Routes.API depending on the 
 * HTTP request method will dictate which method is executed. 
 *  - HTTP GET:  Returns a single customer details
 *  - HTTP POST: Updates a single customers details
 */
@RestController
@RequestMapping(Routes.API)
@Secured("ROLE_USER")
public class CustomersController {
    
    /** CustomerServiceInterface instance from Bean on startup */
    @Autowired
    private CustomerServiceInterface customerService;
    
    /** 
     * When a GET request is received this method is executed. 
     * It looks up the requested customer by ID from the OAuth session 
     * and then queries MongoDB for said customer. 
     * 
     * @param auth the OAuth Authentication for a user's permissions
     * @return ResponseEntity<Customer> JSON the Customer Details as JSON
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Customer> getSingleCustomerDetails(@AuthenticationPrincipal OAuth2Authentication auth) throws APIException {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;

        //Check that the customer has read permissions.
        if (!AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            throw new APIException("No read permissions.");
        }
        
        String customerId = AuthHelper.ID_FROM_AUTH(customerService, auth);
        Customer customer = customerService.requestCustomerDetails(customerId);

        return new ResponseEntity(customer, headers, status);

    }
    
    /**
     * This method looks at the POSTed data from the User and updates their 
     * details. 
     * 
     * The incoming request will contain JSON data that is compatible with the 
     * customer domain and can therefore be easily checked and validated before
     * updating. 
     * 
     * @param auth the OAuth Authentication for a user's permissions
     * @param customer the Customer we want to Update
     * @param builder the URI Components Builder to map Strings to Values. A Spring Framework component.
     * @return ResponseEntity<Customer> JSON
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> updateCustomerDetails(@AuthenticationPrincipal OAuth2Authentication auth, @RequestBody Customer customer, UriComponentsBuilder builder) throws APIException {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.CREATED;

        //Check that the customer has write permissions.
        if (!AuthHelper.CAN_WRITE_FROM_AUTH(auth)) {
            throw new APIException("No write permissions.");
        }
        
        //Check that the customer is valid.
        if (!customer.isValid()) {
            throw new APIException("Customer is not valid.");
        }

        //Get the current customer ID.
        String customerId = AuthHelper.ID_FROM_AUTH(customerService, auth);

        //Get the existing customer object.
        Customer oldCustomer = customerService.requestCustomerDetails(customerId);
        
        //Update the existing customer with the submitted values.
        Customer updatedCustomer = oldCustomer.update(customer);
            
        //Request the update with the service.
        Customer newCustomer = customerService.requestUpdateCustomer(updatedCustomer);       

        return new ResponseEntity(newCustomer, headers, status);
    }
    
}

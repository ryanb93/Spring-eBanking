package rest.controller;

import components.AuthHelper;
import core.domain.Customer;
import core.services.interfaces.CustomerServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import config.Routes;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;

/**
 * Unlike traditional Controllers in an MVC architecture this is a RESTful
 * controller. 
 * This means that it does not have a UI associated with it.
 * The purpose of this controller is to manage customers on the web application.
 * ! Will return all responses as JSON !
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
     * @param auth the OAuth Athentication for a user's permissions
     * @return ResponseEntity<Customer> JSON the Customer Details as JSON
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Customer> getSingleCustomerDetails(@AuthenticationPrincipal OAuth2Authentication auth) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.OK;
        Customer customer = null;

        if (AuthHelper.CAN_READ_FROM_AUTH(auth)) {
            String customerId = AuthHelper.ID_FROM_AUTH(customerService, auth);

            if (customerId == null) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                customer = customerService.requestCustomerDetails(customerId);
            }
        } else {
            status = HttpStatus.FORBIDDEN;
        }

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
     * @param auth the OAuth Athentication for a user's permissions
     * @param customer the Customer we want to Update
     * @param builder the URI Components Builder to map Strings to Values. A Spring Framework component.
     * @return ResponseEntity<Customer> JSON
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Customer> updateCustomerDetails(@AuthenticationPrincipal OAuth2Authentication auth, @RequestBody Customer customer, UriComponentsBuilder builder) {

        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        Customer newCustomer = null;

        if (AuthHelper.CAN_WRITE_FROM_AUTH(auth)) {
            if (!this.isValid(customer)) {
                status = HttpStatus.BAD_REQUEST;
            } else {
                    newCustomer = customerService.requestUpdateCustomer(customer);
                    headers.setLocation(builder.path(Routes.API).buildAndExpand(newCustomer.getCustomerId()).toUri());
                    status = HttpStatus.CREATED;
            }
        } else {
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity(newCustomer, headers, status);
    }
    
    /**
     * This method checks to see that all the required data held on a Customer 
     * is present, before the application processes a customer any further.
     * 
     * @param customer the Customer whose details we are currently checking
     * @return boolean true or false dependent on whether all expected fields are present and correct.
     */
    private boolean isValid(Customer customer) {
        boolean valid = true;
        if (customer.getCustomerId() == null) {
            valid = false;
        } else if (customer.getFirstName() == null) {
            valid = false;
        } else if (customer.getLastName() == null) {
            valid = false;
        } else if (customer.getAddress() == null) {
            valid = false;
        }
        return valid;
    }

}

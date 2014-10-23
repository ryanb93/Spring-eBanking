package rest.controller;

import core.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import rest.config.Routes;

@RestController
@RequestMapping(Routes.DETAILS)
public class DetailsController {

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Customer> getAllAccounts() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

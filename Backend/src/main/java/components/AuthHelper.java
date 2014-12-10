package components;

import core.events.customers.CustomerIdEvent;
import core.events.customers.RequestCustomerIdEvent;
import core.services.CustomerService;
import java.util.Set;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import web.domain.User;

/**
 * A helper class which provides methods to do with user and client authentication.
 */
public class AuthHelper {
    
    /**
     * Gets the ID of the customer from the authentication token.
     * 
     * @param customerService - The customer service object.
     * @param auth - The authentication object.
     * @return The customer associated with this authentication session. Null if not found.
     */
    public static String ID_FROM_AUTH(CustomerService customerService, OAuth2Authentication auth) {        
        String customerId = null;
        //Convert the authentication principal into a User.
        User user = (User)auth.getPrincipal();
        //Create a request for the Customer ID based on the User ID.
        RequestCustomerIdEvent requestCustomerIdEvent = new RequestCustomerIdEvent(user.getId());
        //Send the request event to the service.
        CustomerIdEvent customerIdEvent = customerService.requestCustomerId(requestCustomerIdEvent);
        //If the customer event is not null, get the customer ID.
        if(customerIdEvent != null) {
            customerId = customerIdEvent.getCustomerId();
        }
        //Return the customer ID string.
        return customerId;
    }
    
    /**
     * Checks the scope of the authentication session to see if it has READ access.
     * @param auth - The authentication object to check.
     * @return If the authentication object has read access.
     */
    public static boolean CAN_READ_FROM_AUTH(OAuth2Authentication auth) {
        boolean canRead = false;
        Set<String> scope = auth.getOAuth2Request().getScope();
        if(scope.contains("read")) {
            canRead = true;
        }
        return canRead;
    }

    /**
     * Checks the scope of the authentication session to see if it has WRITE access.
     * @param auth - The authentication object to check.
     * @return If the authentication object has write access.
     */
    public static boolean CAN_WRITE_FROM_AUTH(OAuth2Authentication auth) {
        boolean canWrite = false;
        Set<String> scope = auth.getOAuth2Request().getScope();
        if(scope.contains("write")) {
            canWrite = true;
        }
        return canWrite;
    }    
    
}

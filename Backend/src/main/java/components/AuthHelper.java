package components;

import core.events.customers.CustomerIdEvent;
import core.events.customers.RequestCustomerIdEvent;
import core.services.CustomerService;
import java.util.Set;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import web.domain.User;


public class AuthHelper {
    
    public static String ID_FROM_AUTH(CustomerService customerService, OAuth2Authentication auth) {        
        String customerId = null;
        User user = (User)auth.getPrincipal();
        RequestCustomerIdEvent requestCustomerIdEvent = new RequestCustomerIdEvent(user.getId());
        CustomerIdEvent customerIdEvent = customerService.requestCustomerId(requestCustomerIdEvent);
        if(customerIdEvent != null) customerId = customerIdEvent.getCustomerId();
        return customerId;
    }
    
    public static boolean CAN_READ_FROM_AUTH(OAuth2Authentication auth) {
        boolean canRead = false;
        Set<String> scope = auth.getOAuth2Request().getScope();
        if(scope.contains("read")) canRead = true;
        return canRead;
    }

    public static boolean CAN_WRITE_FROM_AUTH(OAuth2Authentication auth) {
        boolean canWrite = false;
        Set<String> scope = auth.getOAuth2Request().getScope();
        if(scope.contains("write")) canWrite = true;
        return canWrite;
    }    
    
}

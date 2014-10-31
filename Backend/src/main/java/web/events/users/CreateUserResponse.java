package web.events.users;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import static org.springframework.util.Assert.notNull;
import web.domain.ApiUser;


public class CreateUserResponse {

    private ApiUser apiCustomer;
    private OAuth2AccessToken oAuth2AccessToken;

    public CreateUserResponse(){}

    public CreateUserResponse(final ApiUser customer, OAuth2AccessToken oAuth2AccessToken) {
        notNull(customer, "Mandatory argument 'user' missing.");
        notNull(oAuth2AccessToken, "Mandatory argument 'oAuth2AccessToken' missing.");
        this.apiCustomer = customer;
        this.oAuth2AccessToken = oAuth2AccessToken;
    }

    public ApiUser getApiCustomer() {
        return this.apiCustomer;
    }

    public OAuth2AccessToken getOAuth2AccessToken() {
        return this.oAuth2AccessToken;
    }
}

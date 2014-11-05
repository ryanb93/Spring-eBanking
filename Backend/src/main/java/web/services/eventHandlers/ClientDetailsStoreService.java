package web.services.eventHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.config.annotation.configuration.ClientDetailsServiceConfiguration;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import web.repository.ClientDetailsRepository;


public class ClientDetailsStoreService implements ClientDetailsService {

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsRepository.findOne(clientId);
    }
    
}

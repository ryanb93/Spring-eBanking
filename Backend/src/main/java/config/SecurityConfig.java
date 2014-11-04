package config;

import components.CORSFilter;
import components.HierarchicalJsr250Voter;
import components.OAuthRestEntryPoint;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.security.oauth2.provider.client.InMemoryClientDetailsService;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import web.repository.OAuth2AccessTokenRepository;
import web.repository.OAuth2RefreshTokenRepository;
import web.repository.implementation.OAuth2RepositoryTokenStore;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public OAuth2AccessTokenRepository accessTokenRepository;

    @Autowired
    public OAuth2RefreshTokenRepository refreshTokenRepository;    
    
    
//    @Bean
//    public  ClientDetailsUserDetailsService detailsService() {
//        return new ClientDetailsUserDetailsService(clientDetailsService());
//    }           
//    
    
    @Bean
    StandardPasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }  
    
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
    }
    
//    @Bean 
//    public ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter() throws Exception {
//        ClientCredentialsTokenEndpointFilter filter = new ClientCredentialsTokenEndpointFilter();
//        filter.setAuthenticationManager(authenticationManagerBean());
//        return filter;
//    }       
//    
//    @Bean
//    public OAuth2AuthenticationEntryPoint clientAuthenticationEntryPoint() {
//        OAuth2AuthenticationEntryPoint entry = new OAuth2AuthenticationEntryPoint();
//        entry.setTypeName("Basic");
//        return entry;
//    }      

    @Bean
    public OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint() {
        OAuth2AuthenticationEntryPoint entry = new OAuth2AuthenticationEntryPoint();
        entry.setRealmName("Test");
        return entry;
    }    
    
    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }     
    
//    @Bean 
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore());
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setClientDetailsService(new InMemoryClientDetailsService());
//        return tokenServices;
//    }
    
    @Bean
    public OAuth2RepositoryTokenStore tokenStore() {
        return new OAuth2RepositoryTokenStore(accessTokenRepository, refreshTokenRepository);
    }
        
    @Bean
    public CORSFilter corsFilter() {
        return new CORSFilter();
    }

    @Bean
    public OAuthRestEntryPoint oauthRestEntryPoint() {
        return new OAuthRestEntryPoint();
    }    
        
    @Bean
    UnanimousBased accessDecisionManager() {
        List<AccessDecisionVoter> votes = new ArrayList();
        votes.add(roleVote());
        return new UnanimousBased(votes);
    }
    
    @Bean
    RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
        hierarchy.setHierarchy("ROLE_ADMIN > ROLEUSER ROLE_USER > ROLE_GUEST");
        return hierarchy;
    }       
    
    @Bean
    HierarchicalJsr250Voter roleVote() {
        return new HierarchicalJsr250Voter(roleHierarchy());
    }
           
}

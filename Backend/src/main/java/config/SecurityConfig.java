package config;

import components.CORSFilter;
import components.OAuthRestEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import web.repository.interfaces.AccessTokenRepositoryInterface;
import web.repository.interfaces.RefreshTokenRepositoryInterface;
import web.repository.OAuth2RepositoryTokenStore;

/**
 * This class setups up all of the security configuration in the web app.
 * More specifically it sets up:
 *  - OAuth 2.0 Access
 *  - Authentication
 *  - Password encoding
 *  - Cross Origin policies
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    /** Token access from the DB */
    @Autowired
    public AccessTokenRepositoryInterface accessTokenRepository;
    
    /** Token refresh / update from DB */
    @Autowired
    public RefreshTokenRepositoryInterface refreshTokenRepository;

    /**
     * Creates a new PasswordEncoder which makes use of SHA-256
     * @return StandardPasswordEncoder
     */
    @Bean
    StandardPasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    /**
     * Creates a new Entry point and sets the realm to eBanking for our app.
     * @return OAuth2AuthenticationEntryPoint
     */
    @Bean
    public OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint() {
        OAuth2AuthenticationEntryPoint entry = new OAuth2AuthenticationEntryPoint();
        entry.setRealmName("eBanking");
        return entry;
    }

    /**
     * Creates a new OAuth Denier.
     * @return OAuth2AccessDeniedHandler
     */
    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }

    /**
     * Sets up access to the DB for OAuth tokens
     * @return OAuth2RepositoryTokenStore
     */
    @Bean
    public OAuth2RepositoryTokenStore tokenStore() {
        return new OAuth2RepositoryTokenStore(accessTokenRepository, refreshTokenRepository);
    }
    
    /**
     * Endpoint OAuth request handling
     * @return OAuthRestEntryPoint
     */
    @Bean
    public OAuthRestEntryPoint oauthRestEntryPoint() {
        return new OAuthRestEntryPoint();
    }
    
    /**
     * Sets up the Cross Origin filter
     * @return CORSFilter
     */
    @Bean
    public CORSFilter corsFilter() {
        return new CORSFilter();
    }

}

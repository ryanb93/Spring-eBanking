package config;

import components.CORSFilter;
import components.OAuthRestEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
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

    @Bean
    StandardPasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint() {
        OAuth2AuthenticationEntryPoint entry = new OAuth2AuthenticationEntryPoint();
        entry.setRealmName("eBanking");
        return entry;
    }

    @Bean
    public OAuth2AccessDeniedHandler oauthAccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }

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

}

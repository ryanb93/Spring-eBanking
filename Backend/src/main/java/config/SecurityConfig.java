package config;

import components.CORSFilter;
import components.OAuthRestEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenEndpointFilter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import web.repository.OAuth2AccessTokenRepository;
import web.repository.OAuth2RefreshTokenRepository;
import web.repository.implementation.OAuth2RepositoryTokenStore;
import web.services.UserService;
import web.services.eventHandlers.ClientDetailsStoreService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    
    
    @Autowired
    private UserDetailsService userDetailsService;
    
//    @Autowired
//    private AuthenticationProvider clientAuthenticationProvider;
//
//    @Autowired
//    private AuthenticationProvider oauthAuthenticationProvider;
//    
    @Autowired
    private UserService userService;    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OAuth2AuthenticationEntryPoint oauthAuthenticationEntryPoint;
    
    @Autowired
    private OAuth2AuthenticationEntryPoint clientAuthenticationEntryPoint;

    @Autowired
    private OAuth2AccessDeniedHandler oauthAccessDeniedHandler;
    
    
    @Autowired
    private CORSFilter corsFilter;
    
    @Autowired
    private DefaultTokenServices tokenServices;
    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }
        
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/oauth/token")
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                    .anonymous().disable()
                    .httpBasic()
                        .authenticationEntryPoint(oauthAuthenticationEntryPoint)
                        .and()
                    .exceptionHandling()
                        .accessDeniedHandler(oauthAccessDeniedHandler)
                        .and()
//                    .addFilterAfter(corsFilter, LastFilter)
                .antMatcher("/login")
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()                       
                    .anonymous().disable()
                    .httpBasic()
                        .authenticationEntryPoint(clientAuthenticationEntryPoint)
                        .and()
                    .exceptionHandling()
                        .accessDeniedHandler(oauthAccessDeniedHandler)
                        .and()
                .antMatcher("/api/**")
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                        .and()
                    .httpBasic()
                        .authenticationEntryPoint(clientAuthenticationEntryPoint)
                        .and()
//                    .addFilter(resourceServerFilter)
                    .exceptionHandling()
                        .accessDeniedHandler(oauthAccessDeniedHandler)
                        .and();                    
                    
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();
    }  
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
    }
    
    @Bean 
    public ClientCredentialsTokenEndpointFilter clientCredentialsTokenEndpointFilter() throws Exception {
        ClientCredentialsTokenEndpointFilter filter = new ClientCredentialsTokenEndpointFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }       
    
    @Bean
    public OAuth2AuthenticationEntryPoint clientAuthenticationEntryPoint() {
        OAuth2AuthenticationEntryPoint entry = new OAuth2AuthenticationEntryPoint();
        entry.setTypeName("Basic");
        return entry;
    }      

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
            
    @Bean
    public CORSFilter corsFilter() {
        return new CORSFilter();
    }

    @Bean
    public OAuthRestEntryPoint oauthRestEntryPoint() {
        return new OAuthRestEntryPoint();
    }    
    
    
    @Configuration
    @EnableResourceServer
    static class OauthResourceServerConfiguration extends ResourceServerConfigurerAdapter {


        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        public OAuth2AccessTokenRepository accessTokenRepository;

        @Autowired
        public OAuth2RefreshTokenRepository refreshTokenRepository;    
        
        @Autowired
        private ClientDetailsStoreService clientDetailsService;
            
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenServices(defaultTokenServices()).authenticationManager(authenticationManager);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/api/**").access("hasRole('ROLE_USER')")
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        }
        
        @Bean 
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices ts = new DefaultTokenServices();
            ts.setTokenStore(tokenStore());
            ts.setSupportRefreshToken(true);
            ts.setClientDetailsService(clientDetailsService);
            return ts;
        }

        @Bean
        public ResourceServerTokenServices defaultTokenServices() {
            final DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
            defaultTokenServices.setTokenStore(tokenStore());
            return defaultTokenServices;
        }
        
//        @Bean
        public OAuth2RepositoryTokenStore tokenStore() {
            return new OAuth2RepositoryTokenStore(accessTokenRepository, refreshTokenRepository);
        }

    }
        
//    @Bean
//    UnanimousBased accessDecisionManager() {
//        List<AccessDecisionVoter> votes = new ArrayList();
//        votes.add(roleVote());
//        return new UnanimousBased(votes);
//    }
    
//    @Bean
//    RoleHierarchyImpl roleHierarchy() {
//        RoleHierarchyImpl hierarchy = new RoleHierarchyImpl();
//        hierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER ROLE_USER > ROLE_GUEST");
//        return hierarchy;
//    }       
    
//    @Bean
//    HierarchicalJsr250Voter roleVote() {
//        return new HierarchicalJsr250Voter(roleHierarchy());
//    }
           
}

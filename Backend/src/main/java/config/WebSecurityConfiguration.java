package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests().antMatchers("/login").permitAll().and()
                    .authorizeRequests()
                        .anyRequest().hasRole("USER")
                    .and()
                    .exceptionHandling()
                        .accessDeniedPage("/login?authorization_error=true")
                    .and()
                    .csrf()
                        .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable()
                    .logout()
                        .logoutSuccessUrl("/index")
                        .logoutUrl("/logout.do")
                    .and()
                    .formLogin()
                        .usernameParameter("j_username")
                        .passwordParameter("j_password")
                        .failureUrl("/loginauthentication_error=true")
                        .loginPage("/login")
                        .loginProcessingUrl("/login.do");

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}

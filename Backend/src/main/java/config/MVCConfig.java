package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * This class sets up our MVC config to correctly render JSPs.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"rest.controller", "web.controller"})
public class MVCConfig extends WebMvcConfigurerAdapter {
    
    /**
     * This method maps the resources in the WEB-INF folder and renders JSPs. 
     * Only used for the Login page and the adminPanel, without either this would 
     * not be required. 
     *  
     * @return InternalResourceViewResolver
     */
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        // Sets resources to come from WEB-INF
        resolver.setPrefix("/WEB-INF/views/");
        // Sets all files in view with JSP
        resolver.setSuffix(".jsp");
        return resolver;
    }
   
}

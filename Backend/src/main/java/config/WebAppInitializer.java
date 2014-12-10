package config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * This class deals with initialising the Application.
 * It defines what happens when the application:
 *  - starts up
 *  - Sets the Root context
 *  - Configures Springs Model, View, Controller (MVC)
 */
@ComponentScan
@EnableAutoConfiguration
public class WebAppInitializer implements WebApplicationInitializer {
    
    /**
     * Sets up the servlet to start with the WebApplication Context and configures
     * it with the configureSpringMvc()
     * @param servletContext 
     */
    @Override
    public void onStartup(ServletContext servletContext) {
        WebApplicationContext rootContext = createRootContext(servletContext);
        configureSpringMvc(servletContext, rootContext);
    }

    /**
     * Sets Spring to use the Annotations instead of XML
     * @param servletContext
     * @return WebApplicationContext
     */
    private WebApplicationContext createRootContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        servletContext.addListener(new ContextLoaderListener(rootContext));
        servletContext.setInitParameter("defaultHtmlEscape", "true");
        return rootContext;
    }

    /**
     * Sets up Spring MVC to also use the Annotations instead of XML
     * 
     * Set up and uses the MVCConfig class to define the MVC properties and sets
     * the application entry point to /
     * @param servletContext
     * @param rootContext 
     */
    private void configureSpringMvc(ServletContext servletContext, WebApplicationContext rootContext) {
        AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
        mvcContext.register(MVCConfig.class);

        mvcContext.setParent(rootContext);

        ServletRegistration.Dynamic appServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(mvcContext));
        appServlet.setLoadOnStartup(1);
        appServlet.addMapping("/");
    }

}

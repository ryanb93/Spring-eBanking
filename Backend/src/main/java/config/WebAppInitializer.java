package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

  private static Logger LOG = LoggerFactory.getLogger(WebAppInitializer.class);

  @Override
  public void onStartup(ServletContext servletContext) {
    WebApplicationContext rootContext = createRootContext(servletContext);
    configureSpringMvc(servletContext, rootContext);
  }

  private WebApplicationContext createRootContext(ServletContext servletContext) {
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    servletContext.addListener(new ContextLoaderListener(rootContext));
    servletContext.setInitParameter("defaultHtmlEscape", "true");
    return rootContext;
  }
  
  private void configureSpringMvc(ServletContext servletContext, WebApplicationContext rootContext) {
    AnnotationConfigWebApplicationContext mvcContext = new AnnotationConfigWebApplicationContext();
    mvcContext.register(MVCConfig.class);

    mvcContext.setParent(rootContext);

    ServletRegistration.Dynamic appServlet = servletContext.addServlet("dispatcher", new DispatcherServlet(mvcContext));
    appServlet.setLoadOnStartup(1);
    appServlet.addMapping("/");
  }
  

}

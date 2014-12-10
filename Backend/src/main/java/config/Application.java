package config;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * This is the main class that is picked up and run by Spring Boot.
 * In here the main method is run as the Spring application with arguments 
 * and the SSL for HTTPS protocol is defined. 
 * 
 * Since Spring Boot uses embedded Tomcat we have had to define a servlet which 
 * is run that enables HTTPS. Typically this would be defined on the server however
 * here is is defined in the code enableSSLContainer();
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {

    /**
     * The main method of our application. 
     * 
     * This uses Spring Boot to launch our application. With this class as the 
     * runtime class.
     * 
     * @param args - Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    /**
     * This method creates the HTTPS protocol for the application.
     * 
     * Defines the running web application to run on HTTPs on port 8080.
     * It is using our own self signed certificate made with the Java Keytool.
     * 
     * @return EmbeddedServletContainerCustomizer HTTPS Tomcat container.
     * @throws Exception 
     */
    @Bean
    EmbeddedServletContainerCustomizer enableSSLContainer() throws Exception {
        
        //  Gets the current working directory => Java runtime working dir
        final Path currentRelativePath = Paths.get("");
        //  From the currentRelativePath we get the full system path as a String
        final String pwd = currentRelativePath.toAbsolutePath().toString();
        //  Append the working directory (pwd) to include the keystore for Certificate 
        final String pwdKeystoreFile = pwd + "/ebanking.p12";
        //  Passwork for cert keystore
        final String keystorePass = "password";
        //  Type of the Keystore generated
        final String keystoreType = "PKCS12";
        //  Provider. Since Java Keytool was used it signs as below.
        final String keystoreProvider = "SunJSSE";
        //  Set an alias. Defined as tomcat as service running on.
        final String keystoreAlias = "tomcat";
        
        //  Creates EmbeddedServeletContainerCustomizer with SSL enabled using
        //  the above keystore settings 
        return new EmbeddedServletContainerCustomizer() {
            
            /**
             * We override this method to modify the running servlet to use SSL 
             * @param factory 
             */
            @Override
            public void customize(ConfigurableEmbeddedServletContainer factory) {
                if (factory instanceof TomcatEmbeddedServletContainerFactory) {
                    TomcatEmbeddedServletContainerFactory containerFactory = (TomcatEmbeddedServletContainerFactory) factory;
                    containerFactory.addConnectorCustomizers(new TomcatConnectorCustomizer() {

                        @Override
                        public void customize(Connector connector) {
                            connector.setPort(8080);
                            connector.setSecure(true);
                            connector.setScheme("https");

                            Http11NioProtocol sslProtocol = (Http11NioProtocol) connector.getProtocolHandler();

                            sslProtocol.setSSLEnabled(true);
                            sslProtocol.setKeystoreFile(pwdKeystoreFile);
                            sslProtocol.setKeystorePass(keystorePass);
                            sslProtocol.setKeystoreType(keystoreType);
                            sslProtocol.setKeyAlias(keystoreAlias);
                            sslProtocol.setKeystoreProvider(keystoreProvider);
                        }

                    });
                }
            }
        };
    }
}

@Configuration
@ImportResource("/META-INF/spring/application-context.xml")
class XmlImportingConfiguration {
}

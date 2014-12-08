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
 * The entry point to our application.
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {

    /**
     * The main method of our application. This uses Spring Boot to launch our
     * application.
     *
     * @param args - Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

    @Bean
    EmbeddedServletContainerCustomizer enableSSLContainer() throws Exception {

        final Path currentRelativePath = Paths.get("");
        final String pwd = currentRelativePath.toAbsolutePath().toString();
        final String pwdKeystoreFile = pwd + "/ebanking.p12";
        final String keystorePass = "password";
        final String keystoreType = "PKCS12";
        final String keystoreProvider = "SunJSSE";
        final String keystoreAlias = "tomcat";

        return new EmbeddedServletContainerCustomizer() {
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

                            Http11NioProtocol proto = (Http11NioProtocol) connector.getProtocolHandler();

                            proto.setSSLEnabled(true);
                            proto.setKeystoreFile(pwdKeystoreFile);
                            proto.setKeystorePass(keystorePass);
                            proto.setKeystoreType(keystoreType);
                            proto.setKeyAlias(keystoreAlias);
                            proto.setKeystoreProvider(keystoreProvider);
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

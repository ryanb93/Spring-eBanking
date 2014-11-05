package config;

import java.io.File;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;


//public class TomcatConfig {
//
//    @Bean
//    EmbeddedServletContainerCustomizer containerCustomizer(
//            @Value("${keystore.file:src/main/resources/private/keystore}") String keystoreFile,
//            @Value("${keystore.pass:changeit}") final String keystorePass)
//            throws Exception {
//
//        // If you were going to reuse this class in another
//        // application, this is one of the key sections that you
//        // would want to change
//
//        final String absoluteKeystoreFile = new File(keystoreFile)
//                .getAbsolutePath();
//
//        return new EmbeddedServletContainerCustomizer() {
//
//            @Override
//            public void customize(ConfigurableEmbeddedServletContainer container) {
//                TomcatEmbeddedServletContainerFactory tomcat = (TomcatEmbeddedServletContainerFactory) container;
//                tomcat.addConnectorCustomizers(new TomcatConnectorCustomizer() {
//                    @Override
//                    public void customize(Connector connector) {
//                        connector.setPort(8443);
//                        connector.setSecure(true);
//                        connector.setScheme("https");
//
//                        Http11NioProtocol proto = (Http11NioProtocol) connector
//                                .getProtocolHandler();
//                        proto.setSSLEnabled(true);
//                        proto.setKeystoreFile(absoluteKeystoreFile);
//                        proto.setKeystorePass(keystorePass);
//                        proto.setKeystoreType("JKS");
//                        proto.setKeyAlias("tomcat");
//                    }
//                });
//
//            }
//        };
//    }
//    
//}

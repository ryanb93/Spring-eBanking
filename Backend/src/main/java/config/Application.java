package config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * The entry point to our application.
 */
@ComponentScan
@EnableAutoConfiguration
public class Application {
    
    /**
     * The main method of our application. This uses Spring Boot to 
     * launch our application.
     * 
     * @param args - Command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}
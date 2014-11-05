package config;

import org.springframework.boot.SpringApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableMongoRepositories(basePackages = {"core.repository", "web.repository"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
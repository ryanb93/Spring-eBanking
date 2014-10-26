package config;

import com.mongodb.MongoClient;
import core.repository.implementations.TransactionRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

@Configuration
public class MongoConfig {
    
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
       return new SimpleMongoDbFactory(new MongoClient(), "eBanking");
    }
    
}
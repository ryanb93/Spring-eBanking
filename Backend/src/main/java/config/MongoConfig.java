package config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import web.repository.implementation.OAuth2AuthenticationReadConverter;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {
    
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
       return new SimpleMongoDbFactory(new MongoClient(), "eBanking");
    }
    
    @Override
    @Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        OAuth2AuthenticationReadConverter converter = new OAuth2AuthenticationReadConverter();
        converterList.add(converter);
        return new CustomConversions(converterList);
    }

    @Override
    protected String getDatabaseName() {
        return "eBanking";
    }

    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new Mongo("localhost", 27017);
    }
    
}
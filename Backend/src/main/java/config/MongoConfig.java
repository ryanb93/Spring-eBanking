package config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import web.repository.implementation.OAuth2AuthenticationReadConverter;

/**
 * This class sets up the MongoDB in Spring.
 * It allows a connection to be opened to the database for performing transactions.
 */
@Configuration
@EnableMongoRepositories(basePackages = {"core.repository", "web.repository"})
public class MongoConfig extends AbstractMongoConfiguration {

    /**
     * Creates a new factory to connect to our MongoDB connection.
     *
     * @return MongoDbFactory - Factory to create a connection to our database.
     * @throws UnknownHostException - Thrown to indicate that the IP address of
     * a host could not be determined.
     */
    @Bean
    @Override
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClient(), this.getDatabaseName());
    }

    /**
     * Method maps the Client authorisation to an authorisation request.
     * Override needed to convert OAuth2 token back from the database.
     * 
     * We had this problem and were unsure on how to resolve this. From searching
     * online we found the solution by using "OAuth2AuthenticationReadConverter"
     * which if you see the class you will find the reference.
     * 
     * @return CustomConversions
     */
    @Override
    @Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
        OAuth2AuthenticationReadConverter converter = new OAuth2AuthenticationReadConverter();
        converterList.add(converter);
        return new CustomConversions(converterList);
    }

    /**
     * Returns the name of the database we want to connect to in the MongoDb.
     *
     * @return String - database name
     */
    @Override
    protected String getDatabaseName() {
        return "eBanking";
    }

    /**
     * Returns a new Mongo object.
     * The Mongo object is what is used for Database interaction
     * @return Mongo
     * @throws Exception
     */
    @Override
    @Bean
    public Mongo mongo() throws Exception {
        return new Mongo("localhost", 27017);
    }

}

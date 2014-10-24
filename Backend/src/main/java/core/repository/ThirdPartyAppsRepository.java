/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.repository;

import core.domain.Customer;
import core.domain.ThirdPartyApp;
import java.util.List;
import java.util.UUID;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Ben
 */
public interface ThirdPartyAppsRepository extends MongoRepository<ThirdPartyApp, String> {
    
}

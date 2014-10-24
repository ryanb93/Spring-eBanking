/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.repository;

import core.domain.ThirdPartyApp;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author Ben
 */
public interface ThirdPartyAppsRepository {
    
    void EnableAccess(ThirdPartyApp app);

    void RevokePermissions(ThirdPartyApp app);

    void findById(UUID appId);

    List<ThirdPartyApp> findAll();
}

package core.events.thirdPartyApps;

import core.domain.ThirdPartyApp;
import core.events.ReadEvent;
import java.util.Collections;
import java.util.List;

public class AllThirdPartyAppsEvent extends ReadEvent {
    
    private final List<ThirdPartyApp> apps;

    public AllThirdPartyAppsEvent(List<ThirdPartyApp> apps) {
      this.apps = Collections.unmodifiableList(apps);
    }

    public List<ThirdPartyApp> getThirdPartyApps() {
      return this.apps;
    }
   
}

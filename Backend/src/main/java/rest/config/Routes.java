package rest.config;

public class Routes {
    
    public static final String api = "/api";
    public static final String accounts = api + "/accounts";
    public static final String customerAccount = api + accounts + "/{id}";
    public static final String details = api + "/details";
    public static final String app = api + "/apps";
    
}

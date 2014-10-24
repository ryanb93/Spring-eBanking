package rest.config;

public class Routes {
    
    public static final String API = "/api/customers/{customer_id}";
    public static final String ACCOUNTS = API + "/accounts";
    public static final String CUSTOMER_ACCOUNT = "/{account_id}";
    public static final String DETAILS = API + "/details";
    public static final String APP = API + "/apps";
    public static final String APP_ID = "/{app_id}";
    public static final String TRANSACTIONS = ACCOUNTS + CUSTOMER_ACCOUNT + "/transactions";
    
    public static final String CUSTOMER_ADMIN = "/api/customers";
    
}

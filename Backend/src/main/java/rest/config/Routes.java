package rest.config;

public class Routes {
    
    public static final String API = "/api/customers/{customer_id}";
    public static final String ACCOUNTS = API + "/accounts";
    public static final String CUSTOMER_ACCOUNT = "/{account_id}";
    public static final String DETAILS = API + "/details";
    
    public static final String APP = API + "/apps";
    public static final String APP_ID = "/{app_id}";
    public static final String SINGLE_APP = "/singleApp";

    public static final String TRANSACTIONS = ACCOUNTS + CUSTOMER_ACCOUNT + "/transactions";
    
    /**
     * Test Routes - Not for production
     */
    public static final String JSON_ACCOUNT = "/jsonAccount";
    public static final String CUSTOMER_ADMIN = "/api/customers";
    public static final String SINGLE_CUSTOMER = "/api/customers/{id}";
    
}

package rest.config;

public class Routes {
    
    /* Root URL. All Routes will go through this point */
    public static final String API = "/api/customers/{customer_id}";
    
    public static final String ACCOUNTS = API + "/accounts";
    public static final String CUSTOMER_ACCOUNT = "/{account_id}";
    
    /* Transaction Routes */
    public static final String TRANSACTIONS = ACCOUNTS + CUSTOMER_ACCOUNT + "/transactions";
    
    /* Customer Details Routes */
    public static final String DETAILS = API + "/details";
    
    /* Third Party Apps Routes */
    public static final String APP = API + "/apps";
    public static final String APP_ID = "/{app_id}";
    
    /* TODO: Testing routes. Before submission these will be removed! */
    public static final String TEST_CUSTOMER_ADMIN = "/api/customers";
    public static final String TEST_JSON_ACCOUNT = "/jsonAccount";
    public static final String TEST_SINGLE_THIRD_PARTY_APP = "/singleApp";
    public static final String TEST_SINGLE_TRANSACTION = "/singleTransaction";
    
}

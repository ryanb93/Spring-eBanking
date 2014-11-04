package rest.config;

public class Routes {
    
    /* Root URL. All Routes will go through this point */
    public static final String API = "/v1.0/customers";
    
    /* Customer entry point */
    public static final String CUSTOMER = API + "/{customer_id}";
    
    /* Accounts routes */
    public static final String ACCOUNTS = CUSTOMER + "/accounts";
    public static final String CUSTOMER_ACCOUNT = "/{account_id}";
    
    /* Transaction Routes */
    public static final String TRANSACTIONS = ACCOUNTS + CUSTOMER_ACCOUNT + "/transactions";
    public static final String TRANSACTIONS_ID = "/{transaction_id}";
    
    /* Third Party Apps Routes */
    public static final String THIRD_PARTY_APP = CUSTOMER + "/apps";
    public static final String THIRD_PARTY_APP_ID = "/{app_id}";
    
    /* TODO: Testing routes. Before submission these will be removed! */
    public static final String TEST_JSON_CUSTOMER = "/jsonCustomer";
    public static final String TEST_SINGLE_THIRD_PARTY_APP = "/singleApp";
    public static final String TEST_SINGLE_TRANSACTION = "/singleTransaction";
    public static final String ADMIN_PANEL = "/adminPanel";
    
}

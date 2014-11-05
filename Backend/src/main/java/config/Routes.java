package config;

public class Routes {
    
    /* Root URL. All Routes will go through this point */
    public static final String API = "/api/customers";
    
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
    
    /* Web Portal Routes */
    public static final String LOGIN = "/login";
    
    /* Admin Panel Routes */
    public static final String ADMIN_PANEL = "/adminPanel";
    public static final String ADD_CUSTOMER = ADMIN_PANEL + "/addCustomer";
    public static final String REMOVE_CUSTOMER = ADMIN_PANEL + "/removeCustomer";
    public static final String ADD_ACCOUNT = ADMIN_PANEL + "/addAccount";
    public static final String REMOVE_ACCOUNT = ADMIN_PANEL + "/removeAccount";
    public static final String ADD_TRANSACTION = ADMIN_PANEL + "/addTransaction";
    public static final String REMOVE_TRANSACTION = ADMIN_PANEL + "/removeTransaction";
    public static final String ADD_USER = ADMIN_PANEL + "/addUser";
    
}

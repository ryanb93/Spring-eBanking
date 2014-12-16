package config;

/**
 * The global routes file. Defines the endpoints for our API and Web interface.
 */
public class Routes {

    /* Root URL. All Routes will go through this point */
    public static final String API = "/api/customer";

    /* Accounts routes */
    public static final String ACCOUNTS = API + "/accounts";
    public static final String SINGLE_ACCOUNT = "/{account_number}";

    /* Transaction Routes */
    public static final String TRANSACTIONS = ACCOUNTS + SINGLE_ACCOUNT + "/transactions";
    public static final String TRANSACTIONS_ID = "/{transaction_id}";

    /* Web Portal Routes */
    public static final String LOGIN = "/login";

    /* Admin Panel Routes */
    public static final String ADMIN_PANEL = "/adminPanel";
    public static final String ALL_CUSTOMERS = ADMIN_PANEL + "/allCustomers";
    public static final String ADD_CUSTOMER = ADMIN_PANEL + "/addCustomer";
    public static final String REMOVE_CUSTOMER = ADMIN_PANEL + "/removeCustomer";
    public static final String ADD_ACCOUNT = ADMIN_PANEL + "/addAccount";
    public static final String REMOVE_ACCOUNT = ADMIN_PANEL + "/removeAccount";
    public static final String ADD_TRANSACTION = ADMIN_PANEL + "/addTransaction";
    public static final String REMOVE_TRANSACTION = ADMIN_PANEL + "/removeTransaction";
    public static final String ADD_USER = ADMIN_PANEL + "/addUser";

}

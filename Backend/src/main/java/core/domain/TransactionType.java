package core.domain;

/**
 * A class which encapsulates Enum types for Payments.
 *
 */
public enum TransactionType {
    
    CASH("cash"), DEBIT_CARD("debitCard"), CREDIT_CARD("creditCard"), BACS("bacs"), DIRECT_DEBIT("directDebit"), STANDING_ORDER("standingOrder"), PAYPAL("paypal"), OTHER("other");

    private final String value;
    
    private TransactionType(String value) {
        this.value = value;
    }
    
    public static TransactionType fromString(String text) {
      if (text != null) {
        for (TransactionType type : TransactionType.values()) {
          if (text.equalsIgnoreCase(type.value)) {
            return type;
          }
        }
      }
      return null;
    }
}

package core.domain;

/**
 * A class which encapsulates Enum types for Payments.
 */
public enum TransactionType {
    
    CASH("cash"), DEBIT_CARD("debitCard"), CREDIT_CARD("creditCard"), BACS("bacs"), DIRECT_DEBIT("directDebit"), STANDING_ORDER("standingOrder"), PAYPAL("paypal"), OTHER("other");

    /** A field to hold a string representation of the enum */
    private final String value;
    
    /**
     * Creates a transaction type object.
     * 
     * @param value - The String value of the enum. 
     */
    private TransactionType(String value) {
        this.value = value;
    }
    
    /**
     * Method which returns a TransactionType based on the string linked to it.
     * 
     * @param text - The value of the string.
     * @return TransactionType from the text.
     */
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

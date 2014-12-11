package web.domain;

/**
 * A Validation Error class used to when properties entered 
 * on the system are invalid
 */
public class ValidationError {
    
    /*
    * The name of the property that is invalid
    */
    private String propertyName;
    
    /*
    * The value of the property that is invalid 
    */
    private String propertyValue;
    
    /*
    * The message the Validation Error will display
    */
    private String message;

    /**
     * Method to retrieve the invalid Property Name
     * @return String the invalid Property Name
     */
    public String getPropertyName() {
        return propertyName;
    }
    
    /**
     * Method to set the invalid Property Name
     * @param propertyName the invalid Property Name to set
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    /**
     * Method to retrieve the invalid Property Value
     * @return String the invalid Property Value
     */
    public String getPropertyValue() {
        return propertyValue;
    }
    
    /**
     * Method to set the invalid Property Value
     * @param propertyValue the invalid Property Value to set
     */
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
    
    /**
     * Method to retrieve the Validation Error Message
     * @return String the Validation Error Message
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * Method to set the Validation Error Message
     * @param message the Validation Error Message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

package web.domain;

/**
 * 
 */
public class ValidationError {
    
    /** */
    private String propertyName;
    
    /** */
    private String propertyValue;
    
    /** */
    private String message;

    /**
     * 
     * @return String
     */
    public String getPropertyName() {
        return propertyName;
    }
    
    /**
     * 
     * @param propertyName 
     */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }
    
    /**
     * 
     * @return String
     */
    public String getPropertyValue() {
        return propertyValue;
    }
    
    /**
     * 
     * @param propertyValue 
     */
    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
    
    /**
     * 
     * @return String
     */
    public String getMessage() {
        return message;
    }
    
    /**
     * 
     * @param message 
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

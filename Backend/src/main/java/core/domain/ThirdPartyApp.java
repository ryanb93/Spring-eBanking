package core.domain;

import java.util.UUID;

/**
 * A class that represents a third-party application. 
 */
public class ThirdPartyApp {

    private final UUID applicationId;   //A unique ID for the applicaiton.
    private final boolean read;         //If the ThirdPartyApp can read.
    private final boolean write;        //If the ThirdPartyApp can write.
    private String name;                //The name of the application.
    private boolean enabled;            //If the ThirdPartyApp is enabled.
    

    /**
     * Creates a new ThirdPartyApp with a name.
     * The application is then assigned a random ID.
     * 
     * @param name - The name of the application.
     * @param read - If the application can read.
     * @param write - If the application can write.
     */
    public ThirdPartyApp(String name, boolean read, boolean write) {
        super();
        this.setName(name);
        this.applicationId = UUID.randomUUID();
        this.read = read;
        this.write = write;
        this.enabled = true;
    }
    
    public final void setName(String name) {
        if(name == null || name.equals("")) { throw new IllegalArgumentException("Name can not be empty."); }
        this.name = name;
    }
    
    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    public UUID getApplicationId() {
        return this.applicationId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean getRead() {
        return this.read;
    }
    
    public boolean getWrite() {
        return this.write;
    }
    
    public boolean getEnabled() {
        return this.enabled;
    }
}

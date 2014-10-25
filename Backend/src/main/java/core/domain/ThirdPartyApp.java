package core.domain;

import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A class that represents a third-party application.
 */
@Document(collection = "thirdpartyapps")
public class ThirdPartyApp {

    @Id
    private String applicationId;       //A unique ID for the applicaiton.
    
    @NotNull
    private final boolean read;         //If the ThirdPartyApp can read.
    
    @NotNull
    private final boolean write;        //If the ThirdPartyApp can write.
    
    @NotNull
    private String name;                //The name of the application.
    
    @NotNull
    private boolean enabled;            //If the ThirdPartyApp is enabled.

    /**
     * Creates a new ThirdPartyApp with a name. The application is then assigned
     * a random ID.
     *
     * @param name - The name of the application.
     * @param read - If the application can read.
     * @param write - If the application can write.
     */
    public ThirdPartyApp(String name, boolean read, boolean write) {
        super();
        
        this.setName(name);
        this.setEnabled(true);
        this.read = read;
        this.write = write;
    }

    /**
     * Sets the name
     *
     * @param name - The new name.
     */
    public final void setName(String name) {
        
        if (name == null || name.equals("")) {
            throw new IllegalArgumentException("Name can not be empty.");
        }
        
        this.name = name;
    }

    /**
     * Sets the value of enabled.
     *
     * @param enabled - If the application is enabled or not.
     */
    public final void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Gets the application ID
     *
     * @return the application ID
     */
    public String getApplicationId() {
        return this.applicationId;
    }

    /**
     * Gets the application name
     *
     * @return the application name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the status of the read access.
     *
     * @return if the application has read access
     */
    public boolean getRead() {
        return this.read;
    }

    /**
     * Gets the status of the write access.
     *
     * @return if the application has write access.
     */
    public boolean getWrite() {
        return this.write;
    }

    /**
     * Gets the enabled status.
     *
     * @return if the application is enabled.
     */
    public boolean getEnabled() {
        return this.enabled;
    }
}

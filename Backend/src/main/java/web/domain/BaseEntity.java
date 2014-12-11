package web.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

/**
 * 
 */
public abstract class BaseEntity implements Serializable {
    
    /** */
    @Id
    private String id;
    
    /** */
    private Date timeCreated;
    
    /**
     * 
     */
    public BaseEntity() {
        this(UUID.randomUUID().toString());
    }

    /**
     * 
     * @param guid 
     */
    public BaseEntity(String guid) {
        Assert.notNull(guid, "UUID is required");
        id = guid;
        this.timeCreated = new Date();
    }
    
    /**
     * 
     * @return String
     */
    public String getId() {
        return id;
    }
    
    /**
     * 
     * @return Date
     */
    public Date getTimeCreated() {
        return timeCreated;
    }

}

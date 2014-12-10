package web.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.util.Assert;

public abstract class BaseEntity implements Serializable {

    @Id
    private String id;

    private Date timeCreated;

    public BaseEntity() {
        this(UUID.randomUUID().toString());
    }

    public BaseEntity(String guid) {
        Assert.notNull(guid, "UUID is required");
        id = guid;
        this.timeCreated = new Date();
    }

    public String getId() {
        return id;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

}

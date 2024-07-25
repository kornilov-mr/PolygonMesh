package primitive;

import java.util.UUID;

public abstract class IdContains {
    private String id;

    protected IdContains() {
        this.id=UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

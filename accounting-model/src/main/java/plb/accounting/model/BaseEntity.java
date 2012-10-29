package plb.accounting.model;

/**
 * User: pbala
 * Date: 10/29/12 9:28 PM
 */
public abstract class BaseEntity {
    /**
     *
     */
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

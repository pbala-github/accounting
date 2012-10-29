package plb.accounting.model;

/**
 * Created by IntelliJ IDEA.
 * User: panagiotis
 * Date: 10/29/12
 * Time: 9:28 PM
 * To change this template use File | Settings | File Templates.
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

package plb.accounting.model;

/**
 * User: pbala
 * Date: 10/29/12 9:28 PM
 */
public abstract class BaseEntity {
    /**
     *
     */
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}

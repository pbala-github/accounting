package plb.accounting.model;

/**
 * Base Entity to hold the id of all other entities
 * <p/>
 * User: pbala
 * Date: 10/29/12 9:28 PM
 */
public abstract class BaseEntity {

    public abstract Long getId();

    public abstract void setId(Long id);

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + getId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity)) return false;

        BaseEntity that = (BaseEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}

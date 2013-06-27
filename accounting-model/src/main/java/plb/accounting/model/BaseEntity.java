package plb.accounting.model;

import javax.persistence.*;

/**
 * Base Entity to hold the id of all other entities
 * <p/>
 * User: pbala
 * Date: 10/29/12 9:28 PM
 */
@MappedSuperclass
public abstract class BaseEntity {
    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ENTITY_ID")
    private Long id;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}

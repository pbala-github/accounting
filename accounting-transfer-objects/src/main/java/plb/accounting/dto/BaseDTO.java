package plb.accounting.dto;

import java.io.Serializable;

/**
 * User: pbala
 * Date: 11/6/12 9:18 AM
 */
public abstract class BaseDTO implements Serializable {
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
        return "BaseDTO{" +
                "id=" + id +
                '}';
    }
}

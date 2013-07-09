package plb.accounting.model.view;

import java.io.Serializable;

/**
 * @author: pbala
 */
public class ExternalPartyView implements Serializable {

    private Long dbId;
    /**
     *
     */
    private String name;

    /**
     *
     */
    private String vat;

    /**
     *
     */
    private String description;

    public ExternalPartyView(Long id, String name, String vat, String description) {
        this.dbId = id;
        this.name = name;
        this.vat = vat;
        this.description = description;
    }

    public Long getDbId() {
        return dbId;
    }

    public String getName() {
        return name;
    }

    public String getVat() {
        return vat;
    }

    public String getDescription() {
        return description;
    }
}

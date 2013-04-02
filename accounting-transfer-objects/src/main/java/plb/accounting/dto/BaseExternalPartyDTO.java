package plb.accounting.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * User: pbala
 * Date: 11/6/12 9:44 AM
 */
public class BaseExternalPartyDTO extends BaseDTO {
    /**
     *
     */
    @NotNull(message = "{externalParty.name.NotNull}")
    @Size(min = 2,message = "{externalParty.name.MinLength}")
    private String name;

    /**
     *
     */
    private String vat;

    /**
     *
     */
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ExternalParty{" +
                "name='" + name + '\'' +
                ", vat='" + vat + '\'' +
                ", description='" + description + '\'' +
                "} " + super.toString();
    }
}

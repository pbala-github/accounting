package plb.accounting.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import java.util.List;

/**
 * User: pbala
 * Date: 10/29/12  9:31 PM
 */
public class ExternalParty extends BaseEntity{

    /**
     *
     */
    @NotEmpty
    private String name;

    /**
     *
     */
    private String vat;

    /**
     *
     */
    private String description;

    /**
     *
     */
    @Valid
    private List<Transaction> transactions;

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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
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

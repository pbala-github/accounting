package plb.accounting.model;

import javax.persistence.*;
import java.util.List;

/**
 * User: pbala
 * Date: 10/29/12  9:31 PM
 */
@Entity
@Table(name = "EXTERNAL_PARTIES")
public class ExternalParty extends BaseEntity {

    /**
     *
     */
    @Column(name = "EX_PARTY_NAME", nullable = false, unique = true)
    private String name;

    /**
     *
     */
    @Column(name = "EXT_PARTY_VAT", length = 13)
    private String vat;

    /**
     *
     */
    @Column(name = "EXT_PARTY_DESCRIPTION", length = 500)
    private String description;

    /**
     *
     */
    @OneToMany(mappedBy = "relatedParty", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @OrderBy("executionDate asc")
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

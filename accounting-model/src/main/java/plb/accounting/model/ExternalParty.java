package plb.accounting.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
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
    private List<Transaction> transactions = new ArrayList<>();

    /**
     * JPA
     */
    private ExternalParty() {
    }

    /**
     * @param name
     */
    public ExternalParty(String name) {
        setName(name);
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        Assert.hasLength(name);
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

    /**
     * @return an unmodifiable list of transactions related to this
     *         external party
     */
    public List<Transaction> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    /**
     * @param transaction
     */
    public boolean addTransaction(Transaction transaction) {
        Assert.notNull(transaction);
        if (transaction.getRelatedParty() == null) {
            transaction.setRelatedParty(this);
        }
        Assert.isTrue(this == transaction.getRelatedParty());
        return this.transactions.add(transaction);
    }

    /**
     * @param transactions
     */
    public boolean addAllTransactions(List<Transaction> transactions) {
        Assert.notNull(transactions);
        boolean result = true;
        for (Transaction transaction : transactions) {
            result = result && addTransaction(transaction);
        }
        return result;
    }

    /**
     * @param transaction
     */
    public boolean removeTransaction(Transaction transaction) {
        Assert.notNull(transaction);
        Assert.isTrue(this == transaction.getRelatedParty());
        transaction.setRelatedParty(null);
        return this.transactions.remove(transaction);
    }

    /**
     * @param transactions
     */
    public boolean removeAllTransactions(List<Transaction> transactions) {
        Assert.notNull(transactions);
        boolean result = true;
        for (Transaction transaction : transactions) {
            result = result && removeTransaction(transaction);
        }
        return result;
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

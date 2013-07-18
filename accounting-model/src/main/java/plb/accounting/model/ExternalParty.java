package plb.accounting.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
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
@TableGenerator(name = "ExPrt_Ids_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 5)
@SQLDelete(sql = "UPDATE EXTERNAL_PARTIES SET DELETED = '1' where EXT_PARTY_ID = ?")
@Where(clause = "deleted <> '1'")
public class ExternalParty extends BaseEntity {

    @Id
    @Column(name = "EXT_PARTY_ID")
    @GeneratedValue(generator = "ExPrt_Ids_Gen")
    private Long id;
    /**
     *
     */
    @Column(name = "EX_PARTY_NAME", nullable = false)
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

    private char deleted;

    /**
     * JPA
     */
    protected ExternalParty() {
    }

    /**
     * @param name
     */
    public ExternalParty(String name) {
        setName(name);
    }


    @Override
    public Long getId() {
        return id;
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
    boolean addTransaction(Transaction transaction) {
        Assert.notNull(transaction);
        Assert.isTrue(this == transaction.getRelatedParty());
        return this.transactions.add(transaction);
    }

    /**
     * @param transactions
     */
    boolean addAllTransactions(List<Transaction> transactions) {
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
        transaction.removeRelatedParty();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExternalParty)) return false;
        if (!super.equals(o)) return false;

        ExternalParty that = (ExternalParty) o;

        if (!name.equals(that.name)) return false;
        if (vat != null ? !vat.equals(that.vat) : that.vat != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (vat != null ? vat.hashCode() : 0);
        return result;
    }
}

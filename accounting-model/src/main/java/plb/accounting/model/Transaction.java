package plb.accounting.model;

import org.hibernate.annotations.Where;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: pbala
 * Date: 10/29/12 9:31 PM
 */
@Entity
@Table(name = "TRANSACTIONS")
@TableGenerator(name = "Tr_Ids_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 20)
public class Transaction extends BaseEntity {

    @Id
    @Column(name = "TR_ID")
    @GeneratedValue(generator = "Tr_Ids_Gen")
    private Long id;
    /**
     *
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "TR_EXECUTION_DATE", nullable = false)
    private Date executionDate;

    /**
     *
     */
    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "TR_ORIGIN_ACCOUNT", nullable = false)
    private Account originAccount;

    /**
     *
     */
    @ManyToOne(targetEntity = Account.class)
    @JoinColumn(name = "TR_DEST_ACCOUNT", nullable = false)
    private Account destinationAccount;

    /**
     *
     */
    @Column(name = "TR_AMOUNT", nullable = false)
    private BigDecimal amount;

    /**
     *
     */
    @Column(name = "TR_DESCRIPTION", nullable = false, length = 500)
    private String description;

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "TR_REL_PARTY")
    @Where(clause = "deleted <> '1'")
    private ExternalParty relatedParty;


    /**
     * JPA
     */
    protected Transaction() {
    }

    /**
     * @param originAccount
     * @param destinationAccount
     * @param executionDate
     * @param amount
     * @param description
     */
    public Transaction(Account originAccount, Account destinationAccount, Date executionDate, BigDecimal amount, String description) {
        setAmount(amount);
        setDescription(description);
        setOriginAccount(originAccount);
        setDestinationAccount(destinationAccount);
        setExecutionDate(executionDate);
    }


    @Override
    public Long getId() {
        return id;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        //TODO allow future dates???????
        Assert.notNull(executionDate);
        this.executionDate = executionDate;
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        Assert.notNull(originAccount);
        Assert.isTrue(!originAccount.equals(destinationAccount));
        if (this.originAccount != null) {
            this.originAccount.removeOutTransaction(this);
        }
        this.originAccount = originAccount;
        this.originAccount.addOutTransaction(this);
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        Assert.notNull(destinationAccount);
        Assert.isTrue(!destinationAccount.equals(originAccount));
        if (this.destinationAccount != null) {
            this.destinationAccount.removeInTransaction(this);
        }
        this.destinationAccount = destinationAccount;
        this.destinationAccount.addInTransaction(this);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        Assert.notNull(amount);
        Assert.isTrue(BigDecimal.ZERO.compareTo(amount) <= 0);

        if (null != this.originAccount) {
            this.originAccount.removeOutTransaction(this);
        }
        if (null != this.destinationAccount) {
            this.destinationAccount.removeInTransaction(this);
        }
        this.amount = amount;
        if (null != this.originAccount) {
            this.originAccount.addOutTransaction(this);
        }
        if (null != this.destinationAccount) {
            this.destinationAccount.addInTransaction(this);
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        Assert.hasLength(description);
        this.description = description;
    }

    public ExternalParty getRelatedParty() {
        return relatedParty;
    }

    public void setRelatedParty(ExternalParty relatedParty) {
        if (this.relatedParty != null) {
            this.relatedParty.removeTransaction(this);
        }
        this.relatedParty = relatedParty;
        if (this.relatedParty != null) {
            this.relatedParty.addTransaction(this);
        }
    }

    void removeRelatedParty() {
        this.relatedParty = null;
    }

    /**
     *
     */
    public void discard() {
        this.originAccount.removeOutTransaction(this);
        this.destinationAccount.removeInTransaction(this);
        this.originAccount = this.destinationAccount = null;

        if (this.relatedParty != null) {
            this.relatedParty.removeTransaction(this);
        }
        this.relatedParty = null;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "executionDate=" + executionDate +
                ", originAccount=" + originAccount +
                ", description='" + description + '\'' +
                ", destinationAccount=" + destinationAccount +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        if (!super.equals(o)) return false;

        Transaction that = (Transaction) o;

        if (!amount.equals(that.amount)) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (!destinationAccount.equals(that.destinationAccount)) return false;
        if (!executionDate.equals(that.executionDate)) return false;
        if (!originAccount.equals(that.originAccount)) return false;
        if (relatedParty != null ? !relatedParty.equals(that.relatedParty) : that.relatedParty != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + executionDate.hashCode();
        result = 31 * result + originAccount.hashCode();
        result = 31 * result + destinationAccount.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (relatedParty != null ? relatedParty.hashCode() : 0);
        return result;
    }
}

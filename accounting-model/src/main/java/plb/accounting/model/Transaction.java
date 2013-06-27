package plb.accounting.model;

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
public class Transaction extends BaseEntity {
    /**
     *
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "TR_EXECUTION_DATE", nullable = false)
    private Date executionDate;

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "TR_ORIGIN_ACCOUNT", nullable = false)
    private Account originAccount;

    /**
     *
     */
    @ManyToOne
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
    private ExternalParty relatedParty;


    /**
     * JPA
     */
    private Transaction() {
    }

    /**
     * @param originAccount
     * @param destinationAccount
     * @param executionDate
     * @param amount
     * @param description
     */
    public Transaction(Account originAccount, Account destinationAccount, Date executionDate, BigDecimal amount, String description) {
        setOriginAccount(originAccount);
        setDestinationAccount(destinationAccount);
        setExecutionDate(executionDate);
        setAmount(amount);
        setDescription(description);
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        Assert.notNull(executionDate);
        this.executionDate = executionDate;
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        Assert.notNull(originAccount);
        Assert.isTrue(originAccount.getCurrentBalance().subtract(this.amount).compareTo(BigDecimal.ZERO) >= 0);
        this.originAccount = originAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        Assert.notNull(destinationAccount);
        this.destinationAccount = destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        Assert.notNull(amount);
        Assert.isTrue(BigDecimal.ZERO.compareTo(amount) <= 0);
        this.amount = amount;
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
        this.relatedParty = relatedParty;
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
}

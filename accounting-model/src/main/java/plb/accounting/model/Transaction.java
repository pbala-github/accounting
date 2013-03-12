package plb.accounting.model;

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
    @JoinColumn(name = "TR_ORIGIN_ACCOUNT")
    private Account originAccount;

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "TR_DEST_ACCOUNT")
    private Account destinationAccount;

    /**
     *
     */
    @Column(name = "TR_AMOUNT", nullable = false, precision = 2, scale = 10)
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
    private ExternalParty relatedParty;

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public Account getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(Account originAccount) {
        this.originAccount = originAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

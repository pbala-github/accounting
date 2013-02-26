package plb.accounting.dto;

import plb.accounting.dto.validation.TransactionCreation;
import plb.accounting.dto.validation.TransactionCreationConstraint;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: pbala
 * Date: 11/6/12 9:10 AM
 */
@TransactionCreationConstraint(groups = TransactionCreation.class)
@GroupSequence({TransactionDTO.class,TransactionCreation.class})
public class TransactionDTO extends BaseDTO{
    /**
     *
     */
    @NotNull
    private Date executionDate;

    /**
     *
     */
    @NotNull
    @Valid
    private AccountDTO originAccount;

    /**
     *
     */
    @NotNull
    @Valid
    private AccountDTO destinationAccount;

    /**
     *
     */
    @NotNull
    @Min(0)
    private BigDecimal amount;

    /**
     *
     */
    @NotNull
    @Size(min = 3)
    private String description;

    /**
     *
     */
    private BaseExternalPartyDTO relatedParty;

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public AccountDTO getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(AccountDTO originAccount) {
        this.originAccount = originAccount;
    }

    public AccountDTO getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(AccountDTO destinationAccount) {
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

    public BaseExternalPartyDTO getRelatedParty() {
        return relatedParty;
    }

    public void setRelatedParty(BaseExternalPartyDTO relatedParty) {
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

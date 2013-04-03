package plb.accounting.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: pbala
 * Date: 11/6/12 9:10 AM
 */
public class TransactionDTO extends BaseDTO {
    /**
     *
     */
    private Date executionDate;

    /**
     *
     */
    private BaseAccountInfoDTO originAccount;

    /**
     *
     */
    private BaseAccountInfoDTO destinationAccount;

    /**
     *
     */
    private BigDecimal amount;

    /**
     *
     */
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

    public BaseAccountInfoDTO getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(BaseAccountInfoDTO originAccount) {
        this.originAccount = originAccount;
    }

    public BaseAccountInfoDTO getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(BaseAccountInfoDTO destinationAccount) {
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

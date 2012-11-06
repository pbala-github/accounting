package plb.accounting.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: pbala
 * Date: 11/6/12 9:10 AM
 */
public class TransactionDTO extends BaseDTO{
    /**
     *
     */
    private Date executionDate;

    /**
     *
     */
    private BaseAccountDTO originAccount;

    /**
     *
     */
    private BaseAccountDTO destinationAccount;

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
    private BaseExternalOrganizationDTO relatedOrganization;

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public BaseAccountDTO getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(BaseAccountDTO originAccount) {
        this.originAccount = originAccount;
    }

    public BaseAccountDTO getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(BaseAccountDTO destinationAccount) {
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

    public BaseExternalOrganizationDTO getRelatedOrganization() {
        return relatedOrganization;
    }

    public void setRelatedOrganization(BaseExternalOrganizationDTO relatedOrganization) {
        this.relatedOrganization = relatedOrganization;
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

package plb.accounting.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * User: pbala
 * Date: 10/29/12 9:31 PM
 */
public class Transaction extends BaseEntity{
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
    private Account originAccount;

    /**
     *
     */
    @NotNull
    @Valid
    private Account destinationAccount;

    /**
     *
     */
    @NotNull
    @Min(0)
    private BigDecimal amount;

    /**
     *
     */
    @NotEmpty
    private String description;

    /**
     *
     */
    @Valid
    private ExternalOrganization relatedOrganization;

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

    public ExternalOrganization getRelatedOrganization() {
        return relatedOrganization;
    }

    public void setRelatedOrganization(ExternalOrganization relatedOrganization) {
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

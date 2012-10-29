package plb.accounting.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: panagiotis
 * Date: 10/29/12
 * Time: 9:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction extends BaseEntity{
    /**
     *
     */
    private Date executionDate;

    /**
     *
     */
    private Account originAccount;

    /**
     *
     */
    private Account destinationAccount;

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
}

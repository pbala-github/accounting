package plb.accounting.common.search;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: pbala
 * Date: 11/1/12 8:35 AM
 */
public class TransactionSearchCriteria {
    /**
     *
     */
    private Date executionDateFrom;

    /**
     *
     */
    private Date executionDateTo;

    /**
     *
     */
    private long originAccountId;

    /**
     *
     */
    private long destinationAccountId;

    /**
     *
     */
    private BigDecimal amountFrom;

    /**
     *
     */
    private BigDecimal amountTo;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private String orgName;

    /**
     *
      */
    private String orgVat;

    public Date getExecutionDateFrom() {
        return executionDateFrom;
    }

    public void setExecutionDateFrom(Date executionDateFrom) {
        this.executionDateFrom = executionDateFrom;
    }

    public Date getExecutionDateTo() {
        return executionDateTo;
    }

    public void setExecutionDateTo(Date executionDateTo) {
        this.executionDateTo = executionDateTo;
    }

    public long getOriginAccountId() {
        return originAccountId;
    }

    public void setOriginAccountId(long originAccountId) {
        this.originAccountId = originAccountId;
    }

    public long getDestinationAccountId() {
        return destinationAccountId;
    }

    public void setDestinationAccountId(long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }

    public BigDecimal getAmountFrom() {
        return amountFrom;
    }

    public void setAmountFrom(BigDecimal amountFrom) {
        this.amountFrom = amountFrom;
    }

    public BigDecimal getAmountTo() {
        return amountTo;
    }

    public void setAmountTo(BigDecimal amountTo) {
        this.amountTo = amountTo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgVat() {
        return orgVat;
    }

    public void setOrgVat(String orgVat) {
        this.orgVat = orgVat;
    }
}

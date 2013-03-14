package plb.accounting.common.search;

import plb.accounting.common.paging.PaginationInfo;

import java.math.BigDecimal;
import java.util.*;

/**
 * User: pbala
 * Date: 11/1/12 8:35 AM
 */
public class TransactionSearchCriteria extends PaginationInfo{
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
    private Set<Long> originAccountIds = new HashSet<Long> ();

    /**
     *
     */
    private Set<Long> destinationAccountIds = new HashSet<Long>();

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

    /**
     *
      * @param id
     * @return
     */
    public TransactionSearchCriteria addOriginAccountId(long id){
        originAccountIds.add(id);
        return this;
    }

    /**
     *
     * @param id
     * @return
     */
    public TransactionSearchCriteria addDestinationAccountId(long id){
        destinationAccountIds.add(id);
        return this;
    }

    /**
     *
     * @param id
     * @return
     */
    public TransactionSearchCriteria removeOriginAccountId(long id){
        originAccountIds.remove(id);
        return this;
    }

    /**
     *
     * @param id
     * @return
     */
    public TransactionSearchCriteria removeDestinationAccountId(long id){
        destinationAccountIds.remove(id);
        return this;
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean hasOriginAccountId(long id){
        return originAccountIds.contains(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public boolean hasDestinationAccountId(long id){
        return destinationAccountIds.contains(id);
    }


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

    public Set<Long> getOriginAccountIds() {
        return originAccountIds;
    }

    public void setOriginAccountIds(Set<Long> originAccountIds) {
        this.originAccountIds = originAccountIds;
    }

    public Set<Long> getDestinationAccountIds() {
        return destinationAccountIds;
    }

    public void setDestinationAccountIds(Set<Long> destinationAccountIds) {
        this.destinationAccountIds = destinationAccountIds;
    }
}

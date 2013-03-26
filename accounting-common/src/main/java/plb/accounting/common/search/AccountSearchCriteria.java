package plb.accounting.common.search;

import plb.accounting.common.paging.PaginationInfo;
import plb.accounting.dto.AccountTypeEnum;

import java.math.BigDecimal;

/**
 * User: pbala
 * Date: 11/1/12 8:29 AM
 */
public class AccountSearchCriteria extends PaginationInfo {

    /**
     *
     */
    private String accountName;

    /**
     *
     */
    private BigDecimal lowestAccountBalance;

    /**
     *
     */
    private BigDecimal highestAccountBalance;

    /**
     *
     */
    private Long parentAccountId;

    /**
     *
     */
    private AccountTypeEnum accountType;
    /**
     *
     */
    private boolean topParentAccount;

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(Long parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public BigDecimal getHighestAccountBalance() {
        return highestAccountBalance;
    }

    public void setHighestAccountBalance(BigDecimal highestAccountBalance) {
        this.highestAccountBalance = highestAccountBalance;
    }

    public BigDecimal getLowestAccountBalance() {
        return lowestAccountBalance;
    }

    public void setLowestAccountBalance(BigDecimal lowestAccountBalance) {
        this.lowestAccountBalance = lowestAccountBalance;
    }

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }

    public boolean isTopParentAccount() {
        return topParentAccount;
    }

    public void setTopParentAccount(boolean topParentAccount) {
        this.topParentAccount = topParentAccount;
    }
}

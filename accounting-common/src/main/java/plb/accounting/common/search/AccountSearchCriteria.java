package plb.accounting.common.search;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/1/12 8:29 AM
 */
public class AccountSearchCriteria {

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
    private long parentAccountId;


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }



    public long getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(long parentAccountId) {
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
}

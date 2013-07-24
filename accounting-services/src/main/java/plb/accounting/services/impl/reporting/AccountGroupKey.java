package plb.accounting.services.impl.reporting;

import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.view.TransactionView;

/**
 * @author: pbala
 */
public class AccountGroupKey {

    /**
     *
     */
    private String accountName;
    /**
     *
     */
    private Long accountDbId;
    /**
     *
     */
    private AccountTypeEnum accountType;

    public AccountGroupKey(String accountName, Long accountDbId, AccountTypeEnum accountType) {
        this.accountName = accountName;
        this.accountDbId = accountDbId;
        this.accountType = accountType;
    }

    public String getAccountName() {
        return accountName;
    }

    public Long getAccountDbId() {
        return accountDbId;
    }

    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    /**
     * @param transactionView
     * @return
     */
    public static AccountGroupKey fromTransactionViewAsOrigin(TransactionView transactionView) {
        return new AccountGroupKey(transactionView.getOriginAccountName(), transactionView.getOriginAccountDbId(), transactionView.getOriginAccountType());
    }

    /**
     * @param transactionView
     * @return
     */
    public static AccountGroupKey fromTransactionViewAsDestination(TransactionView transactionView) {
        return new AccountGroupKey(transactionView.getDestinationAccountName(), transactionView.getDestinationAccountDbId(), transactionView.getDestinationAccountType());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AccountGroupKey that = (AccountGroupKey) o;

        if (!accountDbId.equals(that.accountDbId)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return accountDbId.hashCode();
    }
}

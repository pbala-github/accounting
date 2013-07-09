package plb.accounting.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author pbala
 */
public interface IAccount {
    String getName();

    void setName(String name);

    BigDecimal getInitialBalance();

    void setInitialBalance(BigDecimal initialBalance);

    BigDecimal getCurrentBalance();

    IAccount getParentAccount();

    void setParentAccount(IAccount parentAccount);

    List<IAccount> getChildrenAccounts();

    boolean addChildrenAccount(Account childrenAccount);

    boolean addAllChildrenAccount(List<Account> childrenAccounts);

    boolean removeChildrenAccount(Account childrenAccount);

    boolean removeAllChildrenAccount(List<Account> childrenAccounts);

    String getDescription();

    void setDescription(String description);

    List<Transaction> getInTransactions();

    AccountTypeEnum getType();

    void setType(AccountTypeEnum type);

    List<Transaction> getOutTransactions();

}

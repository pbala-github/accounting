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

    AccountComposite getParentAccount();

    void setParentAccount(AccountComposite parentAccount);

    List<? extends AbstractAccount> getChildrenAccounts();

//    boolean addChildrenAccount(AbstractAccount childrenAccount);
//
//    boolean addAllChildrenAccount(List<? extends AbstractAccount>childrenAccounts);

//    boolean removeChildrenAccount(AbstractAccount childrenAccount);

//    boolean removeAllChildrenAccount(List<? extends AbstractAccount> childrenAccounts);

    String getDescription();

    void setDescription(String description);

    List<Transaction> getInTransactions();

    AccountTypeEnum getType();

    void setType(AccountTypeEnum type);

    List<Transaction> getOutTransactions();

}

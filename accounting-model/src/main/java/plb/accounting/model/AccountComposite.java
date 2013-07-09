package plb.accounting.model;

import org.springframework.util.Assert;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author pbala
 */
public class AccountComposite extends AbstractAccount {

    /**
     *
     */
    @OneToMany(mappedBy = "parentAccount", cascade = CascadeType.ALL)
    @OrderBy("name ASC")
    private List<IAccount> childrenAccounts = new ArrayList<>();

    /**
     * @param name
     * @param type
     */
    public AccountComposite(String name, AccountTypeEnum type) {
        super(name, type, BigDecimal.ZERO);
    }

    /**
     * JPA
     */
    private AccountComposite() {
    }

    /**
     * @return
     */
    @Override
    public List<IAccount> getChildrenAccounts() {
        return Collections.unmodifiableList(childrenAccounts);
    }

    /**
     * @param childrenAccount
     */
    @Override
    public boolean addChildrenAccount(Account childrenAccount) {
        Assert.notNull(childrenAccount);
        Assert.isTrue(this.getType().equals(childrenAccount.getType()));
        Assert.isTrue(null == childrenAccount.getParentAccount());
        childrenAccount.setParentAccount(this);
        setCurrentBalance(this.getCurrentBalance().add(childrenAccount.getCurrentBalance()));
        setInitialBalance(this.getInitialBalance().add(childrenAccount.getInitialBalance()));
        return this.childrenAccounts.add(childrenAccount);
    }

    /**
     * @param childrenAccounts
     */
    @Override
    public boolean addAllChildrenAccount(List<Account> childrenAccounts) {
        Assert.notNull(childrenAccounts);
        boolean result = true;
        for (Account childrenAccount : childrenAccounts) {
            result = result && addChildrenAccount(childrenAccount);
        }
        return result;
    }

    /**
     * @param childrenAccount
     */
    @Override
    public boolean removeChildrenAccount(Account childrenAccount) {
        Assert.notNull(childrenAccount);
        Assert.isTrue(this == childrenAccount.getParentAccount());
        childrenAccount.setParentAccount(null);
        setCurrentBalance(this.getCurrentBalance().subtract(childrenAccount.getCurrentBalance()));
        setInitialBalance(this.getInitialBalance().subtract(childrenAccount.getInitialBalance()));
        return this.childrenAccounts.remove(childrenAccount);
    }

    /**
     * @param childrenAccounts
     */
    @Override
    public boolean removeAllChildrenAccount(List<Account> childrenAccounts) {
        Assert.notNull(childrenAccounts);
        boolean result = true;
        for (Account childrenAccount : childrenAccounts) {
            result = result && removeChildrenAccount(childrenAccount);
        }
        return result;
    }

    @Override
    public List<Transaction> getInTransactions() {
        return null;
    }

    @Override
    public List<Transaction> getOutTransactions() {
        return null;
    }
}

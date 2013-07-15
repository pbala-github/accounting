package plb.accounting.model;

import org.hibernate.ejb.QueryHints;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author pbala
 */
@Entity
@DiscriminatorValue("ACC_CMPST")
public class AccountComposite extends AbstractAccount {

    /**
     *
     */
    @OneToMany(mappedBy = "parentAccount", cascade = CascadeType.ALL)
    @OrderBy("name ASC")
    private List<AbstractAccount> childrenAccounts = new ArrayList<>();

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
    protected AccountComposite() {
    }

    /**
     * @return
     */
    @Override
    public List<? extends AbstractAccount> getChildrenAccounts() {
        return Collections.unmodifiableList(childrenAccounts);
    }

    /**
     * @param childrenAccount
     */
    boolean addChildrenAccount(AbstractAccount childrenAccount) {
        Assert.notNull(childrenAccount);
        Assert.isTrue(this.getType().equals(childrenAccount.getType()));
        setCurrentBalance(this.getCurrentBalance().add(childrenAccount.getCurrentBalance()));
        setInitialBalance(this.getInitialBalance().add(childrenAccount.getInitialBalance()));
        return this.childrenAccounts.add(childrenAccount);
    }

    /**
     * @param childrenAccounts
     */
    boolean addAllChildrenAccount(List<? extends AbstractAccount> childrenAccounts) {
        Assert.notNull(childrenAccounts);
        boolean result = true;
        for (AbstractAccount childrenAccount : childrenAccounts) {
            result = result && addChildrenAccount(childrenAccount);
        }
        return result;
    }

    /**
     * @param childrenAccount
     */
    boolean removeChildrenAccount(AbstractAccount childrenAccount) {
        Assert.notNull(childrenAccount);
        Assert.isTrue(this == childrenAccount.getParentAccount());
        setCurrentBalance(this.getCurrentBalance().subtract(childrenAccount.getCurrentBalance()));
        setInitialBalance(this.getInitialBalance().subtract(childrenAccount.getInitialBalance()));
        return this.childrenAccounts.remove(childrenAccount);
    }

    /**
     * @param childrenAccounts
     */
    boolean removeAllChildrenAccount(List<? extends AbstractAccount> childrenAccounts) {
        Assert.notNull(childrenAccounts);
        boolean result = true;
        for (AbstractAccount childrenAccount : childrenAccounts) {
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

package plb.accounting.model;

import org.hibernate.ejb.QueryHints;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * User: pbala
 * Date: 10/29/12 9:28 PM
 */
@NamedQueries({
        @NamedQuery(//
                name = "Account.all",//
                query = "select  account from Account  account",
                hints = {//
                        @QueryHint(name = QueryHints.HINT_READONLY, value = "true")//
                }
        ),//
        @NamedQuery(name = "Account.byId", query = "select  account from Account  account where account.id = :id")
})
@Entity
@DiscriminatorValue("ACC")
public class Account extends AbstractAccount {

    /**
     *
     */
    @OneToMany(mappedBy = "destinationAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("executionDate ASC")
    private List<Transaction> inTransactions = new ArrayList<>();
    /**
     *
     */
    @OneToMany(mappedBy = "originAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("executionDate ASC")
    private List<Transaction> outTransactions = new ArrayList<>();

    /**
     * JPA
     */
    protected Account() {
    }

    /**
     * @param name
     * @param type
     * @param initialBalance
     */
    public Account(String name, AccountTypeEnum type, BigDecimal initialBalance) {
        super(name, type, initialBalance);
    }

    /**
     * @param outTransaction
     */
    boolean removeOutTransaction(Transaction outTransaction) {
        Assert.notNull(outTransaction);
        Assert.isTrue(this == outTransaction.getOriginAccount());
        updateParentAccountBalance(outTransaction.getAmount());
        return outTransactions.remove(outTransaction);
    }

    /**
     * @param outTransactions
     */
    boolean removeAllOutTransaction(List<Transaction> outTransactions) {
        Assert.notNull(outTransactions);
        boolean result = true;
        for (Transaction outTransaction : outTransactions) {
            result = result && removeOutTransaction(outTransaction);
        }
        return result;
    }

    /**
     * @return an unmodifiable list of outgoing transactions
     */
    @Override
    public List<Transaction> getOutTransactions() {
        return Collections.unmodifiableList(outTransactions);
    }

    /**
     * @param outTransaction
     */
    boolean addOutTransaction(Transaction outTransaction) {
        Assert.notNull(outTransaction);
        Assert.isTrue(this == outTransaction.getOriginAccount());
        Assert.isTrue(getCurrentBalance().subtract(outTransaction.getAmount()).compareTo(BigDecimal.ZERO) >= 0);
        if (parentAccount != null) {
            parentAccount.removeChildrenAccount(this);
        }
        setCurrentBalance(getCurrentBalance().subtract(outTransaction.getAmount()));
        if (parentAccount != null) {
            parentAccount.addChildrenAccount(this);
        }
        return outTransactions.add(outTransaction);
    }

    /**
     * @param outTransactions
     */
    boolean addAllOutTransaction(List<Transaction> outTransactions) {
        Assert.notNull(outTransactions);
        boolean result = true;
        for (Transaction outTransaction : outTransactions) {
            result = result && addOutTransaction(outTransaction);
        }
        return result;
    }

    /**
     * @param inTransactions
     */
    boolean addAllInTransaction(List<Transaction> inTransactions) {
        Assert.notNull(inTransactions);
        boolean result = true;
        for (Transaction inTransaction : inTransactions) {
            result = result && addInTransaction(inTransaction);
        }
        return result;
    }

    /**
     * @param inTransaction
     */
    boolean removeInTransaction(Transaction inTransaction) {
        Assert.notNull(inTransaction);
        Assert.isTrue(this == inTransaction.getDestinationAccount());
        if (parentAccount != null) {
            parentAccount.removeChildrenAccount(this);
        }
        setCurrentBalance(getCurrentBalance().subtract(inTransaction.getAmount()));
        if (parentAccount != null) {
            parentAccount.addChildrenAccount(this);
        }
        return inTransactions.remove(inTransaction);
    }

    /**
     * @param inTransactions
     */
    boolean removeAllInTransaction(List<Transaction> inTransactions) {
        Assert.notNull(inTransactions);
        boolean result = true;
        for (Transaction inTransaction : inTransactions) {
            result = result && removeInTransaction(inTransaction);
        }
        return result;
    }

    @Override
    public List<? extends AbstractAccount> getChildrenAccounts() {
        return null;
    }

    /**
     * @return an unmodifiable list of incoming transactions
     */
    @Override
    public List<Transaction> getInTransactions() {
        return Collections.unmodifiableList(inTransactions);
    }

    /**
     * @param inTransaction
     */
    boolean addInTransaction(Transaction inTransaction) {
        Assert.notNull(inTransaction);
        Assert.isTrue(this == inTransaction.getDestinationAccount());
        updateParentAccountBalance(inTransaction.getAmount());
        return inTransactions.add(inTransaction);
    }

    private void updateParentAccountBalance(BigDecimal amount) {
        AccountComposite pAccount = parentAccount;
        if (pAccount != null) {
            pAccount.removeChildrenAccount(this);
        }
        setCurrentBalance(getCurrentBalance().add(amount));
        if (pAccount != null) {
            pAccount.addChildrenAccount(this);
        }
    }


}
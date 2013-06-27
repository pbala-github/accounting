package plb.accounting.model;

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
@Entity
@Table(name = "ACCOUNTS", uniqueConstraints = {@UniqueConstraint(columnNames = {"ACC_NAME", "ACC_PARENT_ACCOUNT"})})
public class Account extends BaseEntity {
    /**
     *
     */
    @Column(name = "ACC_NAME", nullable = false)
    private String name;

    /**
     *
     */
    @Column(name = "ACC_INITIAL_BALANCE", nullable = false)
    private BigDecimal initialBalance;

    /**
     *
     */
    @Column(name = "ACC_CURRENT_BALANCE", nullable = false)
    private BigDecimal currentBalance;

    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "ACC_PARENT_ACCOUNT")
    private Account parentAccount;

    /**
     *
     */
    @OneToMany(mappedBy = "parentAccount", cascade = CascadeType.ALL)
    @OrderBy("name ASC")
    private List<Account> childrenAccounts = new ArrayList<>();

    /**
     *
     */
    @Column(name = "ACC_DECRIPTION", length = 500)
    private String description;

    /**
     *
     */
    @OneToMany(mappedBy = "destinationAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("executionDate ASC")
    private List<Transaction> inTransactions;

    /**
     *
     */
    @OneToMany(mappedBy = "originAccount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @OrderBy("executionDate ASC")
    private List<Transaction> outTransactions;
    /**
     *
     */
    @Column(name = "ACC_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum type;

    /**
     * JPA
     */
    private Account() {
    }

    /**
     * @param name
     * @param type
     * @param initialBalance
     */
    public Account(String name, AccountTypeEnum type, BigDecimal initialBalance) {
        setName(name);
        setType(type);
        setInitialBalance(initialBalance);
        setCurrentBalance(initialBalance);
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        Assert.hasLength(name);
        this.name = name;
    }

    /**
     * @return
     */
    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    /**
     * @param initialBalance
     */
    public void setInitialBalance(BigDecimal initialBalance) {
        Assert.notNull(initialBalance);
        Assert.isTrue(BigDecimal.ZERO.compareTo(initialBalance) <= 0);
        this.initialBalance = initialBalance;
    }

    /**
     * @return
     */
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    /**
     * @param currentBalance
     */
    public void setCurrentBalance(BigDecimal currentBalance) {
        Assert.notNull(currentBalance);
        Assert.isTrue(BigDecimal.ZERO.compareTo(initialBalance) <= 0);
        this.currentBalance = currentBalance;
    }

    /**
     * @return
     */
    public Account getParentAccount() {
        return parentAccount;
    }

    /**
     * @param parentAccount
     */
    public void setParentAccount(Account parentAccount) {
        this.parentAccount = parentAccount;
    }

    /**
     * @return
     */
    public List<Account> getChildrenAccounts() {
        return Collections.unmodifiableList(childrenAccounts);
    }

    /**
     * @param childrenAccount
     */
    public boolean addChildrenAccount(Account childrenAccount) {
        Assert.notNull(childrenAccount);
        if (childrenAccount.getParentAccount() == null) {
            childrenAccount.setParentAccount(this);
        }
        Assert.isTrue(this == childrenAccount.getParentAccount());
        return this.childrenAccounts.add(childrenAccount);
    }

    /**
     * @param childrenAccounts
     */
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
    public boolean removeChildrenAccount(Account childrenAccount) {
        Assert.notNull(childrenAccount);
        Assert.isTrue(this == childrenAccount.getParentAccount());
        childrenAccount.setParentAccount(null);
        return this.childrenAccounts.remove(childrenAccount);
    }

    /**
     * @param childrenAccounts
     */
    public boolean removeAllChildrenAccount(List<Account> childrenAccounts) {
        Assert.notNull(childrenAccounts);
        boolean result = true;
        for (Account childrenAccount : childrenAccounts) {
            result = result && removeChildrenAccount(childrenAccount);
        }
        return result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return an unmodifiable list of incoming transactions
     */
    public List<Transaction> getInTransactions() {
        return Collections.unmodifiableList(inTransactions);
    }

    /**
     * @param inTransaction
     */
    public boolean addInTransaction(Transaction inTransaction) {
        Assert.notNull(inTransaction);
        Assert.isTrue(currentBalance.subtract(inTransaction.getAmount()).compareTo(BigDecimal.ZERO) >= 0);
        Assert.isTrue(this == inTransaction.getDestinationAccount());
        setCurrentBalance(getCurrentBalance().add(inTransaction.getAmount()));
        return inTransactions.add(inTransaction);
    }

    /**
     * @param inTransactions
     */
    public boolean addAllInTransaction(List<Transaction> inTransactions) {
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
    public boolean removeInTransaction(Transaction inTransaction) {
        Assert.notNull(inTransaction);
        Assert.isTrue(this == inTransaction.getDestinationAccount());
        setCurrentBalance(getCurrentBalance().subtract(inTransaction.getAmount()));
        return inTransactions.remove(inTransaction);
    }

    /**
     * @param inTransactions
     */
    public boolean removeAllInTransaction(List<Transaction> inTransactions) {
        Assert.notNull(inTransactions);
        boolean result = true;
        for (Transaction inTransaction : inTransactions) {
            result = result && removeInTransaction(inTransaction);
        }
        return result;
    }

    public AccountTypeEnum getType() {
        return type;
    }

    public void setType(AccountTypeEnum type) {
        Assert.notNull(type);
        this.type = type;
    }

    /**
     * @return an unmodifiable list of outgoing transactions
     */
    public List<Transaction> getOutTransactions() {
        return Collections.unmodifiableList(outTransactions);
    }

    /**
     * @param outTransaction
     */
    public boolean addOutTransaction(Transaction outTransaction) {
        Assert.notNull(outTransaction);
        Assert.isTrue(this == outTransaction.getOriginAccount());
        setCurrentBalance(getCurrentBalance().subtract(outTransaction.getAmount()));
        return outTransactions.add(outTransaction);
    }

    /**
     * @param outTransactions
     */
    public boolean addAllOutTransaction(List<Transaction> outTransactions) {
        Assert.notNull(outTransactions);
        boolean result = true;
        for (Transaction outTransaction : outTransactions) {
            result = result && addOutTransaction(outTransaction);
        }
        return result;
    }

    /**
     * @param outTransaction
     */
    public boolean removeOutTransaction(Transaction outTransaction) {
        Assert.notNull(outTransaction);
        Assert.isTrue(this == outTransaction.getOriginAccount());
        setCurrentBalance(getCurrentBalance().add(outTransaction.getAmount()));
        return outTransactions.remove(outTransaction);
    }

    /**
     * @param outTransactions
     */
    public boolean removeAllOutTransaction(List<Transaction> outTransactions) {
        Assert.notNull(outTransactions);
        boolean result = true;
        for (Transaction outTransaction : outTransactions) {
            result = result && removeOutTransaction(outTransaction);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                "} " + super.toString();
    }

}
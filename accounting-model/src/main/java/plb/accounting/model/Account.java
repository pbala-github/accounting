package plb.accounting.model;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private List<Account> childrenAccounts;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(BigDecimal initialBalance) {
        this.initialBalance = initialBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Account getParentAccount() {
        return parentAccount;
    }

    public void setParentAccount(Account parentAccount) {
        this.parentAccount = parentAccount;
    }

    public List<Account> getChildrenAccounts() {
        return childrenAccounts;
    }

    public void setChildrenAccounts(List<Account> childrenAccounts) {
        this.childrenAccounts = childrenAccounts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Transaction> getInTransactions() {
        return inTransactions;
    }

    public void setInTransactions(List<Transaction> inTransactions) {
        this.inTransactions = inTransactions;
    }

    public AccountTypeEnum getType() {
        return type;
    }

    public void setType(AccountTypeEnum type) {
        this.type = type;
    }

    public List<Transaction> getOutTransactions() {
        return outTransactions;
    }

    public void setOutTransactions(List<Transaction> outTransactions) {
        this.outTransactions = outTransactions;
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
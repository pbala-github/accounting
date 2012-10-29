package plb.accounting.model;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: panagiotis
 * Date: 10/29/12
 * Time: 9:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class Account extends BaseEntity{
    /**
     *
     */
    private String name;

    /**
     *
     */
    private BigDecimal initialBalance;

    /**
     *
     */
    private BigDecimal currentBalance;

    /**
     *
     */
    private Account parentAccount;

    /**
     *
     */
    private List<Account> childrenAccounts;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private List<Transaction> transactions;

    /**
     *
     */
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public AccountTypeEnum getType() {
        return type;
    }

    public void setType(AccountTypeEnum type) {
        this.type = type;
    }
}

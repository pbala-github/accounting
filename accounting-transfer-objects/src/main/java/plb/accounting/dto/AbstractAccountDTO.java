package plb.accounting.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/6/12 9:31 AM
 */
public abstract class AbstractAccountDTO<T> extends BaseDTO{

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
    private T parentAccount;

    /**
     *
     */
    private List<T> childrenAccounts;

    /**
     *
     */
    private String description;

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

    public T getParentAccount() {
        return parentAccount;
    }

    public void setParentAccount(T parentAccount) {
        this.parentAccount = parentAccount;
    }

    public List<T> getChildrenAccounts() {
        return childrenAccounts;
    }

    public void setChildrenAccounts(List<T> childrenAccounts) {
        this.childrenAccounts = childrenAccounts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public AccountTypeEnum getType() {
        return type;
    }

    public void setType(AccountTypeEnum type) {
        this.type = type;
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

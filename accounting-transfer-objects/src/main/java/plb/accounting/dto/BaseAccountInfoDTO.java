package plb.accounting.dto;

import java.math.BigDecimal;

/**
 * User: pbala
 * Date: 3/24/13 10:45 PM
 */
public class BaseAccountInfoDTO extends BaseDTO {
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
    private String description;

    /**
     *
     */
    private AccountTypeEnum type;

    private boolean transactional;

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

    public boolean isTransactional() {
        return transactional;
    }

    public void setTransactional(boolean transactional) {
        this.transactional = transactional;
    }

    @Override
    public String toString() {
        return "BaseAccountInfoDTO{" +
                "name='" + name + '\'' +
                ", initialBalance=" + initialBalance +
                ", currentBalance=" + currentBalance +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }

}

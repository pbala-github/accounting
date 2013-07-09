package plb.accounting.model.view;

import plb.accounting.model.AccountTypeEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: pbala
 */
public class AccountView implements Serializable {

    private Long dbId;
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

    public AccountView(Long dbId, String name, BigDecimal initialBalance, BigDecimal currentBalance, String description, AccountTypeEnum type) {
        this.dbId = dbId;
        this.name = name;
        this.initialBalance = initialBalance;
        this.currentBalance = currentBalance;
        this.description = description;
        this.type = type;
    }

    public Long getDbId() {
        return dbId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public String getDescription() {
        return description;
    }

    public AccountTypeEnum getType() {
        return type;
    }
}

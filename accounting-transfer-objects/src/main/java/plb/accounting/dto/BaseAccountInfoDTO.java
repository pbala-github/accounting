package plb.accounting.dto;

import plb.accounting.dto.validation.AccountCreation;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.math.BigDecimal;

/**
 * User: pbala
 * Date: 3/24/13 10:45 PM
 */
public class BaseAccountInfoDTO extends BaseDTO {
    /**
     *
     */
    @NotNull(groups = {Default.class, AccountCreation.class})
    @Size(min = 2, groups = {Default.class, AccountCreation.class})
    private String name;

    /**
     *
     */
    @DecimalMin(value = "0.0", groups = {Default.class, AccountCreation.class})
    private BigDecimal initialBalance;

    /**
     *
     */
    @Min(0)
    private BigDecimal currentBalance;

    /**
     *
     */
    @NotNull(groups = {Default.class, AccountCreation.class})
    private String description;

    /**
     *
     */
    @NotNull
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
        return "BaseAccountInfoDTO{" +
                "name='" + name + '\'' +
                ", initialBalance=" + initialBalance +
                ", currentBalance=" + currentBalance +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
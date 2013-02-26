package plb.accounting.dto;

import plb.accounting.dto.validation.AccountCreation;
import plb.accounting.dto.validation.AccountCreationConstraint;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.math.BigDecimal;

/**
 * User: pbala
 * Date: 1/14/13 2:07 PM
 */
@AccountCreationConstraint(groups = {AccountCreation.class})
public class BaseAccountDTO extends BaseDTO{

    /**
     *
     */
    @NotNull (groups = {Default.class, AccountCreation.class})
    @Size(min = 2,groups = {Default.class, AccountCreation.class})
    private String name;

    /**
     *
     */
    @DecimalMin(value="0.0",groups = {Default.class, AccountCreation.class})
    private BigDecimal initialBalance;

    /**
     *
     */
    @Valid
    private BaseAccountDTO parentAccount;

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

    public BaseAccountDTO getParentAccount() {
        return parentAccount;
    }

    public void setParentAccount(BaseAccountDTO parentAccount) {
        this.parentAccount = parentAccount;
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
        return "NewAccountDTO{" +
                "name='" + name + '\'' +
                ", initialBalance=" + initialBalance +
                ", parentAccount=" + parentAccount +
                ", description='" + description + '\'' +
                ", type=" + type +
                "} " + super.toString();
    }
}

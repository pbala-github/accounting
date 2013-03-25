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
public class BaseAccountDTO extends BaseAccountInfoDTO{

    /**
     *
     */
    @Valid
    private BaseAccountInfoDTO parentAccount;

    public BaseAccountInfoDTO getParentAccount() {
        return parentAccount;
    }

    public void setParentAccount(BaseAccountInfoDTO parentAccount) {
        this.parentAccount = parentAccount;
    }

    @Override
    public String toString() {
        return "BaseAccountDTO{" +
                "parentAccount=" + parentAccount +
                '}';
    }
}

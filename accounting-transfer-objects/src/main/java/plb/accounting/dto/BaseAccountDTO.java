package plb.accounting.dto;

import plb.accounting.dto.validation.AccountCreation;
import plb.accounting.dto.validation.AccountCreationConstraint;

/**
 * User: pbala
 * Date: 1/14/13 2:07 PM
 */
@AccountCreationConstraint(groups = {AccountCreation.class})
public class BaseAccountDTO extends BaseAccountInfoDTO {

    /**
     *
     */
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

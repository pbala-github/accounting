package plb.accounting.dto;

/**
 * User: pbala
 * Date: 1/14/13 2:07 PM
 */
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

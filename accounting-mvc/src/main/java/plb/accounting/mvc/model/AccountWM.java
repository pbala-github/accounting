package plb.accounting.mvc.model;

import plb.accounting.dto.DetailedAccountDTO;

/**
 * User: pbala
 * Date: 12/21/12 4:06 PM
 */
public class AccountWM extends AbstractWM{

    /**
     * Referenced Account
     */
    private DetailedAccountDTO refAccount;

    public DetailedAccountDTO getRefAccount() {
        return refAccount;
    }

    public void setRefAccount(DetailedAccountDTO refAccount) {
        this.refAccount = refAccount;
    }
}

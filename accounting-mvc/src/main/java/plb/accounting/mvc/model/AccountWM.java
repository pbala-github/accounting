package plb.accounting.mvc.model;

import plb.accounting.dto.AccountDTO;

/**
 * User: pbala
 * Date: 12/21/12 4:06 PM
 */
public class AccountWM extends AbstractWM{

    /**
     * Referenced Account
     */
    private AccountDTO refAccount;

    public AccountDTO getRefAccount() {
        return refAccount;
    }

    public void setRefAccount(AccountDTO refAccount) {
        this.refAccount = refAccount;
    }
}

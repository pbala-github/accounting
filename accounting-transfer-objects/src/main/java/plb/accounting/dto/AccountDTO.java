package plb.accounting.dto;

import java.util.List;

/**
 * User: pbala
 * Date: 11/6/12 9:31 AM
 */
public class AccountDTO extends BaseAccountDTO {

    /**
     *
     */
    private List<AccountDTO> childrenAccounts;

    public List<AccountDTO> getChildrenAccounts() {
        return childrenAccounts;
    }

    public void setChildrenAccounts(List<AccountDTO> childrenAccounts) {
        this.childrenAccounts = childrenAccounts;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                "childrenAccounts=" + childrenAccounts +
                '}';
    }
}

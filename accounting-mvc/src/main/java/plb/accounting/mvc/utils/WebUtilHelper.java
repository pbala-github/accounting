package plb.accounting.mvc.utils;

import plb.accounting.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: pbala
 * Date: 12/28/12 2:07 PM
 */
public abstract class WebUtilHelper {

    /**
     *
     * @param accounts
     * @return
     */
    public static List<AccountDTO> serializeAccounts(AccountDTO... accounts){

        List<AccountDTO> serializedAccounts = new ArrayList<AccountDTO>();

        if(accounts != null)
            for(AccountDTO acc : accounts){
                serializedAccounts.add(acc);
                if(acc.getChildrenAccounts() != null && !acc.getChildrenAccounts().isEmpty())
                    serializedAccounts.addAll(serializeAccounts(acc.getChildrenAccounts().toArray(new AccountDTO[0])));
            }

        return serializedAccounts;
    }

}

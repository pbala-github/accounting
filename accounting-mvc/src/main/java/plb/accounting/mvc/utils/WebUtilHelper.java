package plb.accounting.mvc.utils;

import plb.accounting.dto.BaseAccountDTO;

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
    public static List<BaseAccountDTO> serializeAccounts(BaseAccountDTO... accounts){

        List<BaseAccountDTO> serializedAccounts = new ArrayList<BaseAccountDTO>();

        if(accounts != null)
            for(BaseAccountDTO acc : accounts){
                serializedAccounts.add(acc);
                if(acc.getChildrenAccounts() != null && !acc.getChildrenAccounts().isEmpty())
                    serializedAccounts.addAll(serializeAccounts(acc.getChildrenAccounts().toArray(new BaseAccountDTO[0])));
            }

        return serializedAccounts;
    }

}

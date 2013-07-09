import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.ExternalParty;

import java.math.BigDecimal;

/**
 * @author pbala
 */
public class SampleFactory {

    /**
     *
     * @return
     */
    public static Account getOriginAccount() {
        return new Account("origin account 1", AccountTypeEnum.STORAGE, new BigDecimal(2000));
    }

    /**
     *
     * @return
     */
    public static Account getDestinationAccount() {
        return new Account("destination account 1", AccountTypeEnum.OUTCOME, new BigDecimal(0));
    }

    public static ExternalParty getExternalParty() {
        return new ExternalParty("external party 1");
    }
}

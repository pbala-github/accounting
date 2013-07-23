package plb.accounting.services.impl.mapping;

import plb.accounting.common.transformation.AccountingObjectProvider;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.model.AbstractAccount;
import plb.accounting.model.Account;
import plb.accounting.model.AccountComposite;
import plb.accounting.model.AccountTypeEnum;

/**
 * @author: pbala
 */
public class AccountProvider<T extends AbstractAccount> implements AccountingObjectProvider<T> {

    @Override
    public Class<?>[] getSourceTypes() {
        return new Class<?>[]{AccountDTO.class, BaseAccountInfoDTO.class, DetailedAccountDTO.class};
    }

    @Override
    public Class<?>[] getDestinationTypes() {
        return new Class<?>[]{AbstractAccount.class,Account.class, AccountComposite.class};
    }


    @Override
    public T get(ProvisionRequest<T> request) {
        BaseAccountInfoDTO baseAccountInfoDTO = (BaseAccountInfoDTO) request.getSource();

        AbstractAccount account = baseAccountInfoDTO.isTransactional() ?//
                new Account(//
                        baseAccountInfoDTO.getName(),//
                        AccountTypeEnum.valueOf(baseAccountInfoDTO.getType().name()),      //
                        baseAccountInfoDTO.getInitialBalance()) :
                new AccountComposite(baseAccountInfoDTO.getName(),//
                        AccountTypeEnum.valueOf(baseAccountInfoDTO.getType().name()));

        return (T) account;
    }

}

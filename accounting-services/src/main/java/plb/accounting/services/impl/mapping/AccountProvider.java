package plb.accounting.services.impl.mapping;

import plb.accounting.common.transformation.AccountingObjectProviderSupport;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.model.AbstractAccount;
import plb.accounting.model.Account;
import plb.accounting.model.AccountComposite;
import plb.accounting.model.AccountTypeEnum;

/**
 * @author: pbala
 */
public class AccountProvider extends AccountingObjectProviderSupport<AbstractAccount> {

    @Override
    public Class<?> getSourceType() {
        return BaseAccountInfoDTO.class;
    }

    @Override
    public AbstractAccount get(ProvisionRequest<AbstractAccount> request) {
        BaseAccountInfoDTO baseAccountInfoDTO = (BaseAccountInfoDTO) request.getSource();

        AbstractAccount account = baseAccountInfoDTO.isTransactional() ?//
                new Account(//
                        baseAccountInfoDTO.getName(),//
                        AccountTypeEnum.valueOf(baseAccountInfoDTO.getType().name()),      //
                        baseAccountInfoDTO.getInitialBalance()) :
                new AccountComposite(baseAccountInfoDTO.getName(),//
                        AccountTypeEnum.valueOf(baseAccountInfoDTO.getType().name()));

        return account;
    }

}

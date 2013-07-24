package plb.accounting.services.impl.mapping.dto;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.model.AbstractAccount;
import plb.accounting.model.Account;
import plb.accounting.model.view.AccountView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;

/**
 * @author: pbala
 */
@ApplicationScoped
public class AccountDtoFactory {

    @Inject
    protected ITransformationService transformationService;

    /**
     * @param accountView
     * @return
     */
    public BaseAccountInfoDTO toBaseDto(AccountView accountView) {
        return transformationService.transform(accountView, BaseAccountInfoDTO.class);
    }

    /**
     * @param accountView
     * @return
     */
    public AccountDTO toDto(AccountView accountView) {
        return transformationService.transform(accountView, AccountDTO.class);
    }

    /**
     * @param account
     * @return
     */
    public DetailedAccountDTO toDetailedDto(AbstractAccount account) {
        DetailedAccountDTO accountDTO = null;

        if (account != null) {
            accountDTO = transformationService.transform(account, DetailedAccountDTO.class);
//            accountDTO.setTransactions(new ArrayList<TransactionDTO>());
//            if (account.getInTransactions() != null)
//                accountDTO.getTransactions().addAll(transformationService.transform(account.getInTransactions(), TransactionDTO.class));
//            if (account.getOutTransactions() != null)
//                accountDTO.getTransactions().addAll(transformationService.transform(account.getOutTransactions(), TransactionDTO.class));
        }

        return accountDTO;
    }

    /**
     * @param account
     * @return
     */
    public BaseAccountInfoDTO toBaseDto(AbstractAccount account) {
        return transformationService.transform(account, BaseAccountInfoDTO.class);
    }
}

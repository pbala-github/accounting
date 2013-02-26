package plb.accounting.services.impl;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.model.Account;
import plb.accounting.services.IAccountService;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:08 PM
 */
public class AccountService extends BaseService implements IAccountService {


    @Override
    public List<BaseAccountDTO> getAccounts() {
        return transformationService.transform(accountingDAOFacade.getAccounts(),BaseAccountDTO.class);
    }

    @Override
    public DetailedAccountDTO loadAccountById(long accountId) {
        return transformationService.transform(accountingDAOFacade.findAccountById(accountId),DetailedAccountDTO.class);
    }

    @Override
    public BaseAccountDTO saveAccount(BaseAccountDTO accountDTO) {

          Account account = accountingDAOFacade.saveOrUpdateAccount(transformationService.transform(accountDTO,Account.class));

          return transformationService.transform(account,DetailedAccountDTO.class);
    }

    @Override
    public void deleteAccount(long accountId) {
        accountingDAOFacade.deleteAccount(accountId);
    }

    @Override
    public List<BaseAccountDTO> searchAccounts(AccountSearchCriteria criteria) {
        return transformationService.transform(accountingDAOFacade.searchAccounts(criteria),BaseAccountDTO.class);
    }

}

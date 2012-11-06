package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.IAccountingDAOFacade;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.model.Account;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:08 PM
 */
public class AccountService extends BaseService implements IAccountService{


    @Override
    public List<BaseAccountDTO> getAccounts() {
        return transformationService.transform(accountingDAOFacade.getAccounts(),BaseAccountDTO.class);
    }

    @Override
    public AccountDTO loadAccountById(long accountId) {
        return transformationService.transform(accountingDAOFacade.findAccountById(accountId),AccountDTO.class);
    }

    @Override
    public AccountDTO saveAccount(AccountDTO accountDTO) {

          Account account = accountingDAOFacade.saveOrUpdateAccount(transformationService.transform(accountDTO,Account.class));

          return transformationService.transform(account,AccountDTO.class);
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

package plb.accounting.services.impl;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.model.Account;
import plb.accounting.services.IAccountService;

import javax.ejb.EJB;
import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:08 PM
 */
public class AccountService extends BaseService implements IAccountService {

    @EJB
    private AccountDAO dao;

    @Override
    public List<BaseAccountDTO> getAccounts() {
        return transformationService.transform(dao.getAll(Account.class), BaseAccountDTO.class);
    }

    @Override
    public DetailedAccountDTO loadAccountById(long accountId) {
        return transformationService.transform(dao.findById(Account.class, accountId), DetailedAccountDTO.class);
    }

    @Override
    public BaseAccountDTO saveAccount(BaseAccountDTO accountDTO) {

        Account account = dao.saveOrUpdate(transformationService.transform(accountDTO, Account.class));

        return transformationService.transform(account, DetailedAccountDTO.class);
    }

    @Override
    public void deleteAccount(long accountId) {
        dao.delete(Account.class, accountId);
    }

    @Override
    public List<BaseAccountDTO> searchAccounts(AccountSearchCriteria criteria) {
        return transformationService.transform(dao.searchAccounts(criteria), BaseAccountDTO.class);
    }

}

package plb.accounting.services.impl;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.model.Account;
import plb.accounting.services.AccountService;

import javax.ejb.EJB;
import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:08 PM
 */
public class AccountServiceImpl extends BaseService implements AccountService {

    @EJB
    private AccountDAO dao;

    @Override
    public List<BaseAccountInfoDTO> getAccounts() {
        return transformationService.transform(dao.getAll(Account.class), BaseAccountInfoDTO.class);
    }

    @Override
    public DetailedAccountDTO loadAccountById(long accountId) {
        Account account = dao.findById(Account.class, accountId);
        return account != null ? transformationService.transform(account, DetailedAccountDTO.class) : null;
    }

    @Override
    public BaseAccountInfoDTO saveAccount(BaseAccountInfoDTO accountDTO) {
        //new account
        if (accountDTO.getId() == null) {
            accountDTO.setCurrentBalance(accountDTO.getInitialBalance());
        }

        Account account = dao.saveOrUpdate(transformationService.transform(accountDTO, Account.class));
        return transformationService.transform(account, BaseAccountInfoDTO.class);
    }

    @Override
    public void deleteAccount(long accountId) {
        dao.delete(Account.class, accountId);
    }

    @Override
    public List<BaseAccountInfoDTO> searchAccounts(AccountSearchCriteria criteria) {
        return transformationService.transform(dao.searchAccounts(criteria), BaseAccountInfoDTO.class);
    }

    @Override
    public List<AccountDTO> getAccountsTree() {
        return transformationService.transform(dao.getAll(Account.class), AccountDTO.class);
    }
}

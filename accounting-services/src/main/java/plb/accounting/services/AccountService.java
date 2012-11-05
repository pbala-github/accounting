package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.IAccountingDAOFacade;
import plb.accounting.model.Account;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:08 PM
 */
public class AccountService implements IAccountService{

    /**
     *
     */
    private IAccountingDAOFacade accountingDAOFacade;

    @Override
    public List<Account> getAccounts() {
        return accountingDAOFacade.getAccounts();
    }

    @Override
    public Account findAccountById(long accountId) {
        return accountingDAOFacade.findAccountById(accountId);
    }

    @Override
    public Account saveAccount(Account account) {
        if(account.getId() == 0)
            return accountingDAOFacade.persistAccount(account);
        else
            return accountingDAOFacade.updateAccount(account);
    }

    @Override
    public void deleteAccount(long accountId) {
        accountingDAOFacade.deleteAccount(accountId);
    }

    @Override
    public List<Account> searchAccounts(AccountSearchCriteria criteria) {
        return accountingDAOFacade.searchAccounts(criteria);
    }
}

package plb.accounting.dao;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import javax.inject.Inject;
import java.util.List;

/**
 * This the entry point to the DAO components
 *
 * User: pbala
 * Date: 11/5/12 3:44 PM
 */
public class AccountingDAOFacade implements IAccountingDAOFacade{

    @Inject
    private IAccountDAO accountDAO;

    @Inject
    private ITransactionDAO transactionDAO;

    @Inject
    private IExternalPartyDAO externalPartyDAO;


    @Override
    public List<Account> getAccounts() {
        return accountDAO.getAll();
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionDAO.getAll();
    }

    @Override
    public List<ExternalParty> getExternalParties() {
        return externalPartyDAO.getAll();
    }

    @Override
    public Account findAccountById(long accountId) {
        return accountDAO.findById(accountId);
    }

    @Override
    public Transaction findTransactionById(long transactionId) {
        return transactionDAO.findById(transactionId);
    }

    @Override
    public ExternalParty findExternalPartyById(long organizationId) {
        return externalPartyDAO.findById(organizationId);
    }

    @Override
    public Account saveOrUpdateAccount(Account account) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Transaction saveOrUpdateTransaction(Transaction transaction) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ExternalParty saveOrUpdateExternalParty(ExternalParty party) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteAccount(long accountId) {
        accountDAO.delete(accountId);
    }

    @Override
    public void deleteTransaction(long transactionId) {
        transactionDAO.delete(transactionId);
    }

    @Override
    public void deleteExternalParty(long organizationId) {
        externalPartyDAO.delete(organizationId);
    }

    @Override
    public List<Account> searchAccounts(AccountSearchCriteria criteria) {
        return accountDAO.searchAccounts(criteria);
    }

    @Override
    public List<Transaction> searchTransactions(TransactionSearchCriteria criteria) {
        return transactionDAO.searchTransactions(criteria);
    }

    @Override
    public List<ExternalParty> searchExternalParties(ExternalPartySearchCriteria criteria) {
        return externalPartyDAO.searchExternalParties(criteria);
    }
}

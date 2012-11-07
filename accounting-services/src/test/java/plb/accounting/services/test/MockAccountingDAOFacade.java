package plb.accounting.services.test;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.IAccountingDAOFacade;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/7/12 8:57 AM
 */
public class MockAccountingDAOFacade implements IAccountingDAOFacade{


    @Override
    public List<Account> getAccounts() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Transaction> getTransactions() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ExternalOrganization> getExternalOrganizations() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Account findAccountById(long accountId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Transaction findTransactionById(long transactionId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ExternalOrganization findExternalOrganizationById(long organizationId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
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
    public ExternalOrganization saveOrUpdateExternalOrganization(ExternalOrganization organization) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteAccount(long accountId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteTransaction(long transactionId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteExternalOrganization(long organizationId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Account> searchAccounts(AccountSearchCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Transaction> searchTransactions(TransactionSearchCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ExternalOrganization> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }


}

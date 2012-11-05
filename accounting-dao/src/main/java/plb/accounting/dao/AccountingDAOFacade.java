package plb.accounting.dao;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * This the entry point to the DAO components
 *
 * User: pbala
 * Date: 11/5/12 3:44 PM
 */
public class AccountingDAOFacade implements IAccountingDAOFacade{

    /**
     *
     */
    private IAccountDAO accountDAO;

    /**
     *
     */
    private ITransactionDAO transactionDAO;

    /**
     *
     */
    private IExternalOrganizationDAO externalOrganizationDAO;


    @Override
    public List<Account> getAccounts() {
        return accountDAO.getAll();
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionDAO.getAll();
    }

    @Override
    public List<ExternalOrganization> getExternalOrganizations() {
        return externalOrganizationDAO.getAll();
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
    public ExternalOrganization findExternalOrganizationById(long organizationId) {
        return externalOrganizationDAO.findById(organizationId);
    }

    @Override
    public Account persistAccount(Account account) {
        return accountDAO.persist(account);
    }

    @Override
    public Transaction persistTransaction(Transaction transaction) {
        return transactionDAO.persist(transaction);
    }

    @Override
    public ExternalOrganization persistExternalOrganization(ExternalOrganization organization) {
        return externalOrganizationDAO.persist(organization);
    }

    @Override
    public Account updateAccount(Account account) {
        return accountDAO.update(account);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return transactionDAO.update(transaction);
    }

    @Override
    public ExternalOrganization updateExternalOrganization(ExternalOrganization organization) {
        return externalOrganizationDAO.update(organization);
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
    public void deleteExternalOrganization(long organizationId) {
        externalOrganizationDAO.delete(organizationId);
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
    public List<ExternalOrganization> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria) {
        return externalOrganizationDAO.searchExternalOrganizations(criteria);
    }
}

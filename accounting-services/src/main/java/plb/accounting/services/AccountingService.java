package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * Entry point class (facade) for service components
 *
 * User: pbala
 * Date: 11/5/12 4:01 PM
 */
public class AccountingService implements IAccountingService{
    /**
     *
     */
    private IAccountService accountService;

    /**
     *
     */
    private ITransactionService transactionService;

    /**
     *
     */
    private IExternalOrganizationService externalOrganizationService;


    @Override
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionService.getTransactions();
    }

    @Override
    public List<ExternalOrganization> getExternalOrganizations() {
        return externalOrganizationService.getExternalOrganizations();
    }

    @Override
    public Account findAccountById(long accountId) {
        return accountService.findAccountById(accountId);
    }

    @Override
    public Transaction findTransactionById(long transactionId) {
        return transactionService.findTransactionById(transactionId);
    }

    @Override
    public ExternalOrganization findExternalOrganizationById(long organizationId) {
        return externalOrganizationService.findExternalOrganizationById(organizationId);
    }

    @Override
    public Account saveAccount(Account account) {
        return accountService.saveAccount(account);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @Override
    public ExternalOrganization saveExternalOrganization(ExternalOrganization organization) {
        return externalOrganizationService.saveExternalOrganization(organization);
    }

    @Override
    public void deleteAccount(long accountId) {
        accountService.deleteAccount(accountId);
    }

    @Override
    public void deleteTransaction(long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }

    @Override
    public void deleteExternalOrganization(long organizationId) {
        externalOrganizationService.deleteExternalOrganization(organizationId);
    }

    @Override
    public List<Account> searchAccounts(AccountSearchCriteria criteria) {
        return accountService.searchAccounts(criteria);
    }

    @Override
    public List<Transaction> searchTransactions(TransactionSearchCriteria criteria) {
        return transactionService.searchTransactions(criteria);
    }

    @Override
    public List<ExternalOrganization> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria) {
        return externalOrganizationService.searchExternalOrganizations(criteria);
    }
}

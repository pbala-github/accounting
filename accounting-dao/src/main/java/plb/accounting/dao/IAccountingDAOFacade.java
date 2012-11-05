package plb.accounting.dao;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:42 PM
 */
public interface IAccountingDAOFacade {

    List<Account> getAccounts();

    List<Transaction> getTransactions();

    List<ExternalOrganization> getExternalOrganizations();


    Account findAccountById(long accountId);

    Transaction findTransactionById(long transactionId);

    ExternalOrganization findExternalOrganizationById(long organizationId);


    Account persistAccount(Account account);

    Transaction persistTransaction(Transaction transaction);

    ExternalOrganization persistExternalOrganization(ExternalOrganization organization);


    Account updateAccount(Account account);

    Transaction updateTransaction(Transaction transaction);

    ExternalOrganization updateExternalOrganization(ExternalOrganization organization);


    void deleteAccount(long accountId);

    void deleteTransaction(long transactionId);

    void deleteExternalOrganization(long organizationId);


    List<Account> searchAccounts(AccountSearchCriteria criteria);

    List<Transaction> searchTransactions(TransactionSearchCriteria criteria);

    List<ExternalOrganization> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria);
}

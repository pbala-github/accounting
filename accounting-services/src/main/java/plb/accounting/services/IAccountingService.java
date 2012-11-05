package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * This is the entry point interface for the service components
 *
 * User: pbala
 * Date: 10/30/12 9:38 AM
 */
public interface IAccountingService {

    List<Account> getAccounts();
    
    List<Transaction> getTransactions();

    List<ExternalOrganization> getExternalOrganizations();
    

    Account findAccountById(long accountId);

    Transaction findTransactionById(long transactionId);
    
    ExternalOrganization findExternalOrganizationById(long organizationId);
    

    Account saveAccount(Account account);
    
    Transaction saveTransaction(Transaction transaction);
    
    ExternalOrganization saveExternalOrganization(ExternalOrganization organization);
    

    void deleteAccount(long accountId);
    
    void deleteTransaction(long transactionId);
    
    void deleteExternalOrganization(long organizationId);


    List<Account> searchAccounts(AccountSearchCriteria criteria);

    List<Transaction> searchTransactions(TransactionSearchCriteria criteria);

    List<ExternalOrganization> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria);

}

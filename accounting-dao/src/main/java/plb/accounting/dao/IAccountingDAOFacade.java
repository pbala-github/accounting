package plb.accounting.dao;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:42 PM
 */
public interface IAccountingDAOFacade {

    List<Account> getAccounts();

    List<Transaction> getTransactions();

    List<ExternalParty> getExternalParties();


    Account findAccountById(long accountId);

    Transaction findTransactionById(long transactionId);

    ExternalParty findExternalPartyById(long organizationId);


    Account saveOrUpdateAccount(Account account);

    Transaction saveOrUpdateTransaction(Transaction transaction);

    ExternalParty saveOrUpdateExternalParty(ExternalParty party);


    void deleteAccount(long accountId);

    void deleteTransaction(long transactionId);

    void deleteExternalParty(long organizationId);


    List<Account> searchAccounts(AccountSearchCriteria criteria);

    List<Transaction> searchTransactions(TransactionSearchCriteria criteria);

    List<ExternalParty> searchExternalParties(ExternalPartySearchCriteria criteria);
}

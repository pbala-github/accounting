package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:56 PM
 */
public interface ITransactionService {

    List<Transaction> getTransactions();

    Transaction findTransactionById(long transactionId);

    Transaction saveTransaction(Transaction transaction);

    void deleteTransaction(long transactionId);

    List<Transaction> searchTransactions(TransactionSearchCriteria criteria);

}

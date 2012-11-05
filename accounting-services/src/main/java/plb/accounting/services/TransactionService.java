package plb.accounting.services;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.IAccountingDAOFacade;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:13 PM
 */
public class TransactionService implements ITransactionService{

    /**
     *
     */
    private IAccountingDAOFacade accountingDAOFacade;

    @Override
    public List<Transaction> getTransactions() {
        return accountingDAOFacade.getTransactions();
    }

    @Override
    public Transaction findTransactionById(long transactionId) {
        return accountingDAOFacade.findTransactionById(transactionId);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        if(transaction.getId() == 0)
            return accountingDAOFacade.persistTransaction(transaction);
        else
            return accountingDAOFacade.updateTransaction(transaction);
    }

    @Override
    public void deleteTransaction(long transactionId) {
        accountingDAOFacade.deleteTransaction(transactionId);
    }

    @Override
    public List<Transaction> searchTransactions(TransactionSearchCriteria criteria) {
        return accountingDAOFacade.searchTransactions(criteria);
    }
}

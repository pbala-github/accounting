package plb.accounting.services.impl;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.model.Transaction;
import plb.accounting.services.ITransactionService;

import javax.ejb.EJB;
import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:13 PM
 */
public class TransactionService extends BaseService implements ITransactionService {

    @EJB
    private TransactionDAO dao;
    
    @Override
    public List<TransactionDTO> getTransactions() {
        return transformationService.transform(dao.getAll(Transaction.class),TransactionDTO.class);
    }

    @Override
    public TransactionDTO findTransactionById(long transactionId) {

        return transformationService.transform(dao.findById(Transaction.class,transactionId),TransactionDTO.class);
    }

    @Override
    public TransactionDTO saveTransaction(TransactionDTO transaction) {
        Transaction t = dao.saveOrUpdate(transformationService.transform(transaction,Transaction.class));
        return transformationService.transform(t,TransactionDTO.class);
    }

    @Override
    public void deleteTransaction(long transactionId) {
        dao.delete(Transaction.class,transactionId);
    }

    @Override
    public List<TransactionDTO> searchTransactions(TransactionSearchCriteria criteria) {
        return transformationService.transform(dao.searchTransactions(criteria),TransactionDTO.class);
    }
}

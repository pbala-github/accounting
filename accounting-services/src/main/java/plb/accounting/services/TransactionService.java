package plb.accounting.services;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.IAccountingDAOFacade;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:13 PM
 */
public class TransactionService extends BaseService implements ITransactionService{


    @Override
    public List<TransactionDTO> getTransactions() {
        return transformationService.transform(accountingDAOFacade.getTransactions(),TransactionDTO.class);
    }

    @Override
    public TransactionDTO findTransactionById(long transactionId) {

        return transformationService.transform(accountingDAOFacade.findTransactionById(transactionId),TransactionDTO.class);
    }

    @Override
    public TransactionDTO saveTransaction(TransactionDTO transaction) {
        Transaction t = accountingDAOFacade.saveOrUpdateTransaction(transformationService.transform(transaction,Transaction.class));
        return transformationService.transform(t,TransactionDTO.class);
    }

    @Override
    public void deleteTransaction(long transactionId) {
        accountingDAOFacade.deleteTransaction(transactionId);
    }

    @Override
    public List<TransactionDTO> searchTransactions(TransactionSearchCriteria criteria) {
        return transformationService.transform(accountingDAOFacade.searchTransactions(criteria),TransactionDTO.class);
    }
}

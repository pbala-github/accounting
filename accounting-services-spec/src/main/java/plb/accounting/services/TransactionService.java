package plb.accounting.services;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.TransactionDTO;

import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 3:56 PM
 */
public interface TransactionService {

    List<TransactionDTO> getTransactions();

    TransactionDTO findTransactionById(long transactionId);

    TransactionDTO saveTransaction(TransactionDTO transaction);

    void deleteTransaction(long transactionId);

    List<TransactionDTO> searchTransactions(TransactionSearchCriteria criteria);

}

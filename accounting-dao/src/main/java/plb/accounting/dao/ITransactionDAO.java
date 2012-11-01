package plb.accounting.dao;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 10/30/12 3:43 PM
 */
public interface ITransactionDAO extends IDAO<Transaction>{

    List<Transaction> searchTransactions(TransactionSearchCriteria searchCriteria);
}

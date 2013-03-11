package plb.accounting.dao;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 3/11/13 2:13 PM
 */
public interface TransactionDAO extends EntityDAO {
    List<Transaction> searchTransactions(TransactionSearchCriteria criteria);
}

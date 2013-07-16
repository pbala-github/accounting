package plb.accounting.dao;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Transaction;
import plb.accounting.model.view.AccountView;
import plb.accounting.model.view.TransactionView;

import java.util.List;

/**
 * User: pbala
 * Date: 3/11/13 2:13 PM
 */
public interface TransactionDAO extends EntityDAO {
    List<TransactionView> searchTransactions(TransactionSearchCriteria criteria);

    List<TransactionView> getAll();
}

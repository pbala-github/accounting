package plb.accounting.dao.impl.file;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.ITransactionDAO;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 10/30/12 3:39 PM
 */
public class FileTransactionDAO implements ITransactionDAO {
    @Override
    public Transaction findById(long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void persist(Transaction obj) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Transaction obj) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Transaction> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Transaction> searchTransactions(TransactionSearchCriteria searchCriteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

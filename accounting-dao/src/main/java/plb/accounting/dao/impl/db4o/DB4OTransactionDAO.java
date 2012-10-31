package plb.accounting.dao.impl.db4o;

import plb.accounting.dao.ITransactionDAO;
import plb.accounting.model.BaseEntity;
import plb.accounting.model.Transaction;

import java.beans.Beans;

/**
 * User: pbala
 * Date: 10/31/12 1:42 PM
 */
public class DB4OTransactionDAO extends DB4OBaseDAO<Transaction> implements ITransactionDAO{

    @Override
    public Transaction findById(long id) {
        Transaction transaction = new Transaction();
        transaction.setId(id);

        return getUnique(transaction);
    }

}

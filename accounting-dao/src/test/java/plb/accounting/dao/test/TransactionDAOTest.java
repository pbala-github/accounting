package plb.accounting.dao.test;

import org.junit.Test;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.model.Transaction;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * User: pbala
 * Date: 11/2/12 2:30 PM
 */
public abstract class TransactionDAOTest extends AbstractDAOTest<TransactionDAO>{
    @Override
    public void persist() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void findById() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Test
    @Override
    public void getAll() {
        List<Transaction> transactions = getDAO().getAll(Transaction.class);

        assertEquals(5,transactions.size());
    }

    @Test
    @Override
    public void searchByCriteria() {
        TransactionSearchCriteria criteria = new TransactionSearchCriteria();

        criteria.setDescription("tr_description_1");

        List<Transaction> transactions = getDAO().searchTransactions(criteria);

        assertEquals(1,transactions.size());

        assertEquals("tr_description_1",transactions.get(0).getDescription());

    }
}

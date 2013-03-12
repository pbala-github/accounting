package plb.accounting.dao.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.concurrent.Callable;

/**
 * User: pbala
 * Date: 3/12/13 2:50 PM
 */
public class TxWrappingImpl implements TxWrappingLocalBusiness {

    private EntityManager em;

    @Override
    public <V> V wrapInTx(Callable<V> task) throws IllegalArgumentException {
        EntityTransaction transaction = em.getTransaction();
        V call = null;

        transaction.begin();
        try {
            call = task.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            transaction.commit();
        }

        return call;
    }
}

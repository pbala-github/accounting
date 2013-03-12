package plb.accounting.dao.test;

import java.util.concurrent.Callable;

/**
 * User: pbala
 * Date: 3/12/13 2:15 PM
 */
public interface TxWrappingLocalBusiness {

    /**
     * Wraps the specified tasks in a new Transaction
     *
     * @param task
     * @throws IllegalArgumentException If no tasks are specified
     */
    <V> V wrapInTx(Callable<V> task) throws IllegalArgumentException;


}

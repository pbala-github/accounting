package plb.accounting.dao.test;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.model.Account;
import plb.accounting.model.BaseEntity;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * User: pbala
 * Date: 3/11/13 4:51 PM
 */
public class JpaAccountDAOWrapper implements AccountDAO {

    AccountDAO delegate;
    TxWrappingLocalBusiness txWrapper;

    @Override
    public List<Account> searchAccounts(final AccountSearchCriteria criteria) {
        return txWrapper.wrapInTx(new Callable<List<Account>>() {

            @Override
            public List<Account> call() throws Exception {
                return delegate.searchAccounts(criteria);
            }
        });

    }

    @Override
    public <T extends BaseEntity> T findById(final Class<T> clazz, final long id) {
        return txWrapper.wrapInTx(new Callable<T>() {

            @Override
            public T call() throws Exception {
                return delegate.findById(clazz, id);
            }
        });

    }

    @Override
    public <T extends BaseEntity> T saveOrUpdate(final T obj) {
        return txWrapper.wrapInTx(new Callable<T>() {
            @Override
            public T call() throws Exception {
                return delegate.saveOrUpdate(obj);
            }
        });

    }

    @Override
    public <T extends BaseEntity> void delete(final Class<T> clazz, final long id) {
        txWrapper.wrapInTx(new Callable<T>() {
            @Override
            public T call() throws Exception {
                delegate.delete(clazz, id);
                return null;
            }
        });

    }

    @Override
    public <T extends BaseEntity> List<T> getAll(final Class<T> clazz) {
        return txWrapper.wrapInTx(new Callable<List<T>>() {
            @Override
            public List<T> call() throws Exception {
                return delegate.getAll(clazz
                );
            }
        });
    }

}

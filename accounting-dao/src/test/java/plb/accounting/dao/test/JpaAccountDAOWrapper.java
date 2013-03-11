package plb.accounting.dao.test;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.model.Account;
import plb.accounting.model.BaseEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * User: pbala
 * Date: 3/11/13 4:51 PM
 */
public class JpaAccountDAOWrapper implements AccountDAO {

    AccountDAO delegate;
    EntityManager em;

    @Override
    public List<Account> searchAccounts(AccountSearchCriteria criteria) {
        em.getTransaction().begin();
        List<Account> accounts = delegate.searchAccounts(criteria);
        em.getTransaction().commit();
        return accounts;
    }

    @Override
    public <T extends BaseEntity> T findById(Class<T> clazz, long id) {
        return delegate.findById(clazz, id);
    }

    @Override
    public <T extends BaseEntity> T saveOrUpdate(T obj) {
        return delegate.saveOrUpdate(obj);
    }

    @Override
    public <T extends BaseEntity> void delete(Class<T> clazz, long id) {
        delegate.delete(clazz, id);
    }

    @Override
    public <T extends BaseEntity> List<T> getAll(Class<T> clazz) {
        return delegate.getAll(clazz);
    }
}

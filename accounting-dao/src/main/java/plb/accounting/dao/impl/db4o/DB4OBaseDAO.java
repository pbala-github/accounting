package plb.accounting.dao.impl.db4o;

import EDU.purdue.cs.bloat.util.Assert;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import plb.accounting.dao.IDAO;
import plb.accounting.model.Account;
import plb.accounting.model.BaseEntity;

import java.util.List;

/**
 * User: pbala
 * Date: 10/31/12 1:14 PM
 */
public abstract class DB4OBaseDAO<T extends BaseEntity> implements IDAO<T>{

    private ObjectContainer db;

    protected DB4OBaseDAO() {
        db = AccountingObjectContainer.get();
    }

    @Override
    public T findById(final long id) {

      return getUnique(new Predicate<T>() {
                @Override
                public boolean match(T o) {
                    return o.getId() == id;
                }
              });
    }

    public void persist(T obj) {
        getDb().store(obj);
    }

    @Override
    public void update(T obj) {

        Assert.isNotNull(obj, "Cannot update a null object");

        T found = findById(obj.getId());

        if(found == null)
            throw new RuntimeException("The object does not exist in DB.");


        getDb().store(obj);
    }

    @Override
    public void delete(long id) {
        BaseEntity entity = findById(id);

        if(entity == null)
            throw new RuntimeException("Entity not found in DB.");

        getDb().delete(entity);
    }

    @Override
    public List<T> getAll() {

        ObjectSet<T> results = getDb().query(getObjectClass());

        return results.subList(0,results.size());
    }

    protected abstract Class<T> getObjectClass();

    protected T getUnique(Predicate<T> predicate){

        ObjectSet<T> results = getDb().query(predicate);

        if(results.size() > 1)
            throw new RuntimeException("A single object expected, but many were found.");

        return results.next();
    }

    protected List<T> executeQuery(Predicate<T> predicate){

        ObjectSet<T> accounts = getDb().query(predicate);

        return accounts.subList(0,accounts.size());
    }



    public ObjectContainer getDb() {
        return db;
    }

}

package plb.accounting.dao.impl.db4o;

import EDU.purdue.cs.bloat.util.Assert;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import plb.accounting.dao.IDAO;
import plb.accounting.model.Account;
import plb.accounting.model.BaseEntity;

/**
 * User: pbala
 * Date: 10/31/12 1:14 PM
 */
public abstract class DB4OBaseDAO<T extends BaseEntity> implements IDAO<T>{

    private ObjectContainer db;

    protected DB4OBaseDAO() {
        db = AccountingObjectContainer.get();
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

    protected T getUnique(T template){
        ObjectSet<T> results = getDb().queryByExample(template);

        return results.next();
    }


    public ObjectContainer getDb() {
        return db;
    }

}

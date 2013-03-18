package plb.accounting.dao.impl.db4o;

import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.query.Predicate;
import plb.accounting.common.validation.AccountingValidator;
import plb.accounting.common.validation.IAccountingValidator;
import plb.accounting.common.validation.ValidationErrorList;
import plb.accounting.common.validation.ValidationException;
import plb.accounting.dao.EntityDAO;
import plb.accounting.model.BaseEntity;

import javax.inject.Inject;
import java.util.List;

/**
 * User: pbala
 * Date: 10/31/12 1:14 PM
 */
public abstract class DB4OBaseDAO implements EntityDAO {

    private ObjectContainer db;

    @Inject
    private IAccountingValidator validator;

    protected DB4OBaseDAO() {
        db = AccountingObjectContainer.get();
    }

    @Override
    public <T extends BaseEntity> T findById(Class<T> clazz, final long id) {

        T obj = getDb().ext().getByID(id);
        getDb().ext().activate(obj, 10);
        return obj;

    }

    @Override
    public <T extends BaseEntity> T saveOrUpdate(T obj) {

        validate(obj);

        if (obj.getId() == 0) {
            getDb().store(obj);
            long id = getDb().ext().getID(obj);

            obj.setId(id);
            getDb().store(obj);
        } else {
            T found = (T) findById(obj.getClass(), obj.getId());

            if (found == null)
                throw new RuntimeException("The object does not exist in DB.");

            getDb().store(obj);

        }

        return obj;
    }


    @Override
    public <T extends BaseEntity> void delete(Class<T> clazz, long id) {
        BaseEntity entity = findById(clazz, id);

        if (entity == null)
            throw new RuntimeException("Entity not found in DB.");

        getDb().delete(entity);
    }

    @Override
    public <T extends BaseEntity> List<T> getAll(Class<T> clazz) {

        ObjectSet<T> objectSet = getDb().query(clazz);

        return objectSet.subList(0, objectSet.size());
    }





    protected <T extends BaseEntity> T getUnique(Predicate<T> predicate) {

        ObjectSet<T> results = getDb().query(predicate);

        if (results.size() > 1)
            throw new RuntimeException("A single object expected, but many were found.");

        return results.next();
    }

    protected <T extends BaseEntity> List<T> executeQuery(Predicate<T> predicate) {

        ObjectSet<T> accounts = getDb().query(predicate);

        return accounts.subList(0, accounts.size());
    }

    public ObjectContainer getDb() {
        return db;
    }

    private <T extends BaseEntity> void validate(T entity) {

        ValidationErrorList errors = this.validator.validate(entity);
        if (!errors.getErrors().isEmpty())
            throw new ValidationException(errors);
    }
}

package plb.accounting.dao;

import plb.accounting.model.BaseEntity;

import java.util.List;

/**
 * User: pbala
 * Date: 10/30/12 3:28 PM
 */
public interface EntityDAO {

    <T extends BaseEntity> T findById(Class<T> clazz, long id);

    <T extends BaseEntity> T saveOrUpdate(T obj);

    <T extends BaseEntity> void delete(Class<T> clazz, long id);

}

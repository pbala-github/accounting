package plb.accounting.dao;

import plb.accounting.model.BaseEntity;

import java.util.List;

/**
 * User: pbala
 * Date: 10/30/12 3:28 PM
 */
public interface IDAO<T extends BaseEntity> {

    T findById(long id);
    
    T saveOrUpdate(T obj);
    
    void delete(long  id);
    
    List<T> getAll();

}

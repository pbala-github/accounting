package plb.accounting.dao;

/**
 * User: pbala
 * Date: 10/30/12 3:28 PM
 */
public interface IDAO<T> {

    T findById(long id);
    
    void persist(T obj);
    
    void delete(long  id);
    
    void update(T obj);

}

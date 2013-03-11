package plb.accounting.dao.test;

import plb.accounting.dao.EntityDAO;

/**
 * User: pbala
 * Date: 10/31/12 2:58 PM
 */
public abstract class AbstractDAOTest<D extends EntityDAO> {

    public abstract void persist();

    public abstract void findById();

    public abstract void delete();

    public abstract void update();

    public abstract void getAll();

    public abstract void searchByCriteria();
    
    protected abstract D getDAO();

    protected static void bootstrap(){

    }
}

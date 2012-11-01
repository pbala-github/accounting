package plb.accounting.dao.test;

import org.junit.BeforeClass;
import plb.accounting.dao.IDAO;
import plb.accounting.dao.impl.db4o.AccountingObjectContainer;

/**
 * User: pbala
 * Date: 10/31/12 2:58 PM
 */
public abstract class AbstractDAOTest<D extends IDAO> {

    @BeforeClass
    public static void setup(){
        System.setProperty(AccountingObjectContainer.FILE_PATH_PROPERTY,"/home/panagiotis/myProjects/accounting/testDB");
        DataBootstrap.bootstrap(AccountingObjectContainer.get());
    }


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

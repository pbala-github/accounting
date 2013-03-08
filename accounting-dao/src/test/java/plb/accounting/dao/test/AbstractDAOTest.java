package plb.accounting.dao.test;

import org.apache.commons.lang.StringUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import plb.accounting.dao.EntityDAO;
import plb.accounting.dao.impl.db4o.AccountingObjectContainer;

import java.io.File;

/**
 * User: pbala
 * Date: 10/31/12 2:58 PM
 */
public abstract class AbstractDAOTest<D extends EntityDAO> {

    @BeforeClass
    public static void setup(){
        //The db file path is provided as a system property
//        System.setProperty(AccountingObjectContainer.FILE_PATH_PROPERTY,"/home/panagiotis/myProjects/accounting/testDB");

        clearDb();
        DataBootstrap.bootstrap(AccountingObjectContainer.get());

    }

    @AfterClass
    public static void tearDown(){
        clearDb();
    }

    private static void clearDb(){
        String dbFile = System.getProperty(AccountingObjectContainer.FILE_PATH_PROPERTY);

        if(StringUtils.isNotEmpty(dbFile)){
            File file = new File(dbFile);
            if(file.exists())
                file.delete();

        }
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

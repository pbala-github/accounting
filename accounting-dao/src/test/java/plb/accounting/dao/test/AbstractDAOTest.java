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
        System.setProperty(AccountingObjectContainer.FILE_PATH_PROPERTY,"/home/pbala/myProjects/accounting/testDB");
    }
}

package plb.accounting.dao.impl.db4o;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;

/**
 * User: pbala
 * Date: 10/31/12 4:38 PM
 */
public final class AccountingObjectContainer {

    public static final String FILE_PATH_PROPERTY = "dbFilePath";

    private static ObjectContainer objectContainer;

    public static synchronized ObjectContainer get(){

        if(objectContainer == null) initialize();

        return objectContainer;
    }

    private static void initialize() {
        String filePath = System.getProperty(FILE_PATH_PROPERTY);
        objectContainer = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), filePath);
    }
}

package plb.accounting.dao.impl.db4o;

import com.db4o.Db4o;
import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.internal.config.EmbeddedConfigurationImpl;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.ArrayList;

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

        EmbeddedConfiguration config = Db4oEmbedded.newConfiguration();
        config.common().objectClass(Account.class).cascadeOnUpdate(true);
        config.common().objectClass(Account.class).cascadeOnActivate(true);
        config.common().objectClass(Account.class).cascadeOnDelete(true);
        config.common().objectClass(Account.class).callConstructor(false);
        config.common().objectClass(Transaction.class).callConstructor(false);
        config.common().objectClass(Transaction.class).cascadeOnDelete(true);
        config.common().objectClass(ArrayList.class).callConstructor(false);
        config.common().exceptionsOnNotStorable(false);

        config.common().objectClass(ExternalOrganization.class).cascadeOnDelete(true);

        objectContainer = Db4oEmbedded.openFile(config, filePath);
    }
}

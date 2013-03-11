package plb.accounting.dao.impl.file;

import plb.accounting.model.Account;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * User: pbala
 * Date: 10/30/12 4:05 PM
 */
public abstract class FileBaseDAO {

    private ObjectsContainer objectsContainer;

    private void init() {
        File file = new File("/home/pbala/accounting");
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            objectInputStream = new ObjectInputStream(fileInputStream);

            objectsContainer = (ObjectsContainer) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist. Create it!");
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            try {
                if (objectInputStream != null)
                    objectInputStream.close();
                else if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            if (objectsContainer == null)
                objectsContainer = new ObjectsContainer();
        }
    }


    protected List<Account> getAccounts() {

        return null;
    }

    static class ObjectsContainer {
        private Set<Account> accounts;
        private Set<Transaction> transactions;
        private Set<ExternalParty> externalParties;


    }
}

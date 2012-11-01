package plb.accounting.dao.impl.file;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.IAccountDAO;
import plb.accounting.model.Account;

import java.util.List;

/**
 * User: pbala
 * Date: 10/30/12 3:36 PM
 */
public class FileAccountDAO implements IAccountDAO {


    @Override
    public Account findById(long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void persist(Account obj) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(Account obj) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Account> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Account> searchAccounts(AccountSearchCriteria searchCriteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

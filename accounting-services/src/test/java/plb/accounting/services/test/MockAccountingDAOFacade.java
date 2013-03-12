package plb.accounting.services.test;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.model.Account;
import plb.accounting.model.BaseEntity;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/7/12 8:57 AM
 */
public class MockAccountingDAOFacade implements AccountDAO, TransactionDAO, ExternalPartyDAO {

    @Override
    public List<Account> searchAccounts(AccountSearchCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ExternalParty> searchExternalParties(ExternalPartySearchCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Transaction> searchTransactions(TransactionSearchCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends BaseEntity> T findById(Class<T> clazz, long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends BaseEntity> T saveOrUpdate(T obj) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends BaseEntity> void delete(Class<T> clazz, long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends BaseEntity> List<T> getAll(Class<T> clazz) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

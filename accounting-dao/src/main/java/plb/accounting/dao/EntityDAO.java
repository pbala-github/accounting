package plb.accounting.dao;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.BaseEntity;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 10/30/12 3:28 PM
 */
public interface EntityDAO {

    <T extends BaseEntity> T findById(Class<T> clazz, long id);

    <T extends BaseEntity> T saveOrUpdate(T obj);

    <T extends BaseEntity> void delete(Class<T> clazz,long  id);

    <T extends BaseEntity> List<T> getAll(Class<T> clazz);

    List<Account> searchAccounts(AccountSearchCriteria criteria);

    List<Transaction> searchTransactions(TransactionSearchCriteria criteria);

    List<ExternalParty> searchExternalParties(ExternalPartySearchCriteria criteria);

}

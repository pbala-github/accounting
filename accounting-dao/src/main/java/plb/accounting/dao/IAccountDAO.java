package plb.accounting.dao;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.model.Account;

import java.util.List;

/**
 * User: pbala
 * Date: 10/30/12 3:42 PM
 */
public interface IAccountDAO extends IDAO<Account>{

    List<Account> searchAccounts(AccountSearchCriteria searchCriteria);
}

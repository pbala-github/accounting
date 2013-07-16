package plb.accounting.dao;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.view.AccountView;

import java.util.List;

/**
 * User: pbala
 * Date: 3/11/13 2:13 PM
 */
public interface AccountDAO extends EntityDAO {
    List<AccountView> searchAccounts(AccountSearchCriteria criteria);

    List<AccountView> getAll();
}

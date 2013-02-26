package plb.accounting.dao.impl.db4o;

import com.db4o.query.Predicate;
import org.apache.commons.lang.StringUtils;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.IAccountDAO;
import plb.accounting.model.Account;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 10/31/12 1:32 PM
 */
public class DB4OAccountDAO extends DB4OBaseDAO<Account> implements IAccountDAO{

    @Override
    public List<Account> searchAccounts(final AccountSearchCriteria searchCriteria) {
        Predicate predicate = new Predicate<Account>() {
            @Override
            public boolean match(Account candidate) {
                return (StringUtils.isEmpty(searchCriteria.getAccountName()) ||
                        candidate.getName().toLowerCase().contains(searchCriteria.getAccountName().toLowerCase())) &&
                        (searchCriteria.getLowestAccountBalance() == null ||
                            searchCriteria.getLowestAccountBalance().compareTo(candidate.getCurrentBalance()) <= 0) &&
                        (searchCriteria.getHighestAccountBalance() == null ||
                                searchCriteria.getHighestAccountBalance().compareTo(candidate.getCurrentBalance()) >= 0) &&
                        (searchCriteria.getParentAccountId() == 0 ||
                                (candidate.getParentAccount() != null &&
                                        searchCriteria.getParentAccountId() == candidate.getParentAccount().getId()));
            }
        };

          return executeQuery(predicate);
    }

    @Override
    public void delete(long id) {
        Account account = findById(id);

        if(account == null)
            throw new RuntimeException("Entity not found in DB.");

        if(account.getTransactions() != null)
            for(Transaction transaction : account.getTransactions())
            getDb().delete(transaction);

        getDb().delete(account);
    }

    @Override
    protected Class<Account> getObjectClass() {
        return Account.class;
    }
}

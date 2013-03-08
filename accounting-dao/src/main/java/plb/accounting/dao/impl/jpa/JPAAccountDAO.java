package plb.accounting.dao.impl.jpa;

import org.springframework.util.StringUtils;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.IAccountDAO;
import plb.accounting.model.Account;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: pbala
 * Date: 2/27/13 4:50 PM
 */
public class JPAAccountDAO extends JPABaseDAO implements IAccountDAO {

    @Override
    public List<Account> searchAccounts(AccountSearchCriteria searchCriteria) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteria = builder.createQuery(Account.class);
        Root<Account> root = criteria.from(Account.class);

        List<Predicate> conditions = new ArrayList<Predicate>();

        if (StringUtils.hasText(searchCriteria.getAccountName()))
            conditions.add(builder.equal(root.get("name"), searchCriteria.getAccountName()));
        if (searchCriteria.getAccountName() != null)
            conditions.add(builder.equal(root.get("type"), searchCriteria.getAccountType()));
        if (searchCriteria.getHighestAccountBalance() != null)
            conditions.add(builder.lessThanOrEqualTo(root.<BigDecimal>get("currentBalance"), searchCriteria.getHighestAccountBalance()));
        if (searchCriteria.getLowestAccountBalance() != null)
            conditions.add(builder.greaterThanOrEqualTo(root.<BigDecimal>get("currentBalance"), searchCriteria.getLowestAccountBalance()));
        if (searchCriteria.getParentAccountId() != 0l)
            conditions.add(builder.equal(root.<Account>get("parentAccount").<Long>get("id"), searchCriteria.getParentAccountId()));

        if (!conditions.isEmpty())
            criteria.where(builder.and(conditions.toArray(new Predicate[0])));


        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Account findById(long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Account saveOrUpdate(Account obj) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Account> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

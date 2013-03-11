package plb.accounting.dao.impl.jpa;

import org.springframework.util.StringUtils;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.model.Account;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * User: pbala
 * Date: 3/11/13 2:15 PM
 */
@Stateless
@Local(AccountDAO.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class JpaAccountDAOImpl extends JPAEntityDaoImpl implements AccountDAO {

    @Override
    public List<Account> searchAccounts(AccountSearchCriteria searchCriteria) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
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

        criteria.select(builder.construct(Account.class));

        TypedQuery<Account> typedQuery = em.createQuery(criteria);
        return typedQuery.getResultList();
    }
}

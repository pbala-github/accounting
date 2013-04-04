package plb.accounting.dao.impl.jpa;

import org.springframework.util.StringUtils;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;

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
public class JpaAccountDAO extends JPAEntityDao implements AccountDAO {

//    /**
//     * Query API implementation
//     *
//     * @param searchCriteria
//     * @return
//     */
//    @Override
//    public List<Account> searchAccounts(AccountSearchCriteria searchCriteria) {
//        QueryBuilder qb = QueryBuilder.forClass(Account.class);
//
//        if (StringUtils.hasText(searchCriteria.getAccountName()))
//            qb.and("name", searchCriteria.getAccountName());
//
//        if (searchCriteria.getAccountType() != null)
//            qb.and("type", AccountTypeEnum.valueOf(searchCriteria.getAccountType().name()));
//
//        if (searchCriteria.getLowestAccountBalance() != null)
//            qb.greaterThan("currentBalance", searchCriteria.getLowestAccountBalance());
//
//        if (searchCriteria.getHighestAccountBalance() != null)
//            qb.lessThan("currentBalance", searchCriteria.getHighestAccountBalance());
//
//        if (searchCriteria.getParentAccountId() != null)
//            qb.and("parentAccount.id", searchCriteria.getParentAccountId());
//
//        if(searchCriteria.isTopParentAccount())
//            qb.nill("parentAccount");
//
//        return qb.build(em, searchCriteria).getResultList();
//    }

    //Criteria API implementation
    @Override
    public List<Account> searchAccounts(AccountSearchCriteria searchCriteria) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Account> criteria = builder.createQuery(Account.class);
        Root<Account> root = criteria.from(Account.class);

        List<Predicate> conditions = new ArrayList<Predicate>();

        if (StringUtils.hasText(searchCriteria.getAccountName()))
            conditions.add(builder.equal(root.get("name"), searchCriteria.getAccountName()));

        if (searchCriteria.getAccountType() != null)
            conditions.add(builder.equal(root.get("type"), AccountTypeEnum.valueOf(searchCriteria.getAccountType().name())));

        if (searchCriteria.getHighestAccountBalance() != null)
            conditions.add(builder.lessThanOrEqualTo(root.<BigDecimal>get("currentBalance"), searchCriteria.getHighestAccountBalance()));

        if (searchCriteria.getLowestAccountBalance() != null)
            conditions.add(builder.greaterThanOrEqualTo(root.<BigDecimal>get("currentBalance"), searchCriteria.getLowestAccountBalance()));

        if (searchCriteria.getParentAccountId() != null)
            conditions.add(builder.equal(root.<Account>get("parentAccount").<Long>get("id"), searchCriteria.getParentAccountId()));

        if (searchCriteria.isTopParentAccount()) {
            conditions.add(builder.isNull(root.get("parentAccount")));
        }

        if (!conditions.isEmpty())
            criteria.where(builder.and(conditions.toArray(new Predicate[0])));

        criteria.select(root);

        TypedQuery<Account> typedQuery = em.createQuery(criteria);
        return typedQuery.getResultList();
    }
}

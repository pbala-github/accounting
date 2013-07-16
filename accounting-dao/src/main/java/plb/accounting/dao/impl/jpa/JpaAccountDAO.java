package plb.accounting.dao.impl.jpa;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.Account_;
import plb.accounting.model.view.AccountView;

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

    /**
     * Search Accounts using advanced criteria
     *
     * @param searchCriteria
     * @return
     */
    @Override
    public List<AccountView> searchAccounts(AccountSearchCriteria searchCriteria) {
        Assert.notNull(searchCriteria);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<AccountView> criteria = builder.createQuery(AccountView.class);
        Root<Account> root = criteria.from(Account.class);

        List<Predicate> conditions = new ArrayList<Predicate>();

        if (StringUtils.hasText(searchCriteria.getAccountName()))
            conditions.add(builder.equal(root.get(Account_.name), searchCriteria.getAccountName()));

        if (searchCriteria.getAccountType() != null)
            conditions.add(builder.equal(root.get(Account_.type), AccountTypeEnum.valueOf(searchCriteria.getAccountType().name())));

        if (searchCriteria.getHighestAccountBalance() != null)
            conditions.add(builder.lessThanOrEqualTo(root.<BigDecimal>get(Account_.currentBalance), searchCriteria.getHighestAccountBalance()));

        if (searchCriteria.getLowestAccountBalance() != null)
            conditions.add(builder.greaterThanOrEqualTo(root.<BigDecimal>get(Account_.currentBalance), searchCriteria.getLowestAccountBalance()));

        if (searchCriteria.getParentAccountId() != null)
            conditions.add(builder.equal(root.<Account>get(Account_.parentAccount.getName()).<Long>get(Account_.id), searchCriteria.getParentAccountId()));

        if (searchCriteria.isTopParentAccount()) {
            conditions.add(builder.isNull(root.get(Account_.parentAccount)));
        }

        if (!conditions.isEmpty())
            criteria.where(builder.and(conditions.toArray(new Predicate[0])));

        criteria.select(builder.construct(AccountView.class,//
                root.get(Account_.id),//
                root.get(Account_.name),//
                root.get(Account_.initialBalance),//
                root.get(Account_.currentBalance),//
                root.get(Account_.description),//
                root.get(Account_.type)//
        ));

        TypedQuery<AccountView> typedQuery = em.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public List<AccountView> getAll() {
        return searchAccounts(new AccountSearchCriteria());
    }

}

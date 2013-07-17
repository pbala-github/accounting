package plb.accounting.dao.impl.jpa;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;
import plb.accounting.model.view.TransactionView;

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
import java.util.Date;
import java.util.List;

/**
 * User: pbala
 * Date: 3/11/13 2:16 PM
 */
@Stateless
@Local(TransactionDAO.class)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class JpaTransactionDAO extends JPAEntityDao implements TransactionDAO {

    /**
     * Search Transactions using advanced criteria
     *
     * @param criteria
     * @return
     */
    @Override
    public List<TransactionView> searchTransactions(TransactionSearchCriteria criteria) {
        Assert.notNull(criteria);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<TransactionView> criteriaQuery = builder.createQuery(TransactionView.class);
        Root<Transaction> root = criteriaQuery.from(Transaction.class);

        List<Predicate> conditions = new ArrayList<Predicate>();

        if (StringUtils.hasText(criteria.getOrgVat()))
            conditions.add(builder.equal(root.<ExternalParty>get("relatedParty").<String>get("vat"), criteria.getOrgVat()));

        if (criteria.getAmountFrom() != null)
            conditions.add(builder.greaterThanOrEqualTo(root.<BigDecimal>get("amount"), criteria.getAmountFrom()));

        if (criteria.getAmountTo() != null)
            conditions.add(builder.lessThanOrEqualTo(root.<BigDecimal>get("amount"), criteria.getAmountTo()));

        if (StringUtils.hasText(criteria.getDescription()))
            conditions.add(builder.like(root.<String>get("description"), criteria.getDescription()));

        if (criteria.getDestinationAccountIds() != null && !criteria.getDestinationAccountIds().isEmpty()) {
            conditions.add(builder.in(root.get("destinationAccount").<Long>get("id")).in(criteria.getDestinationAccountIds()));
        }

        if (criteria.getExecutionDateFrom() != null)
            conditions.add(builder.greaterThanOrEqualTo(root.<Date>get("executionDate"), criteria.getExecutionDateFrom()));

        if (criteria.getExecutionDateTo() != null)
            conditions.add(builder.lessThanOrEqualTo(root.<Date>get("executionDate"), criteria.getExecutionDateTo()));

        if (StringUtils.hasText(criteria.getOrgName()))
            conditions.add(builder.like(root.get("relatedParty").<String>get("name"), criteria.getOrgName()));

        if (criteria.getOriginAccountIds() != null && !criteria.getOriginAccountIds().isEmpty()) {
            conditions.add(root.get("originAccount").<Long>get("id").in(criteria.getOriginAccountIds()));
        }

        if (!conditions.isEmpty())
            criteriaQuery.where(builder.and(conditions.toArray(new Predicate[0])));

        criteriaQuery.select(builder.construct(TransactionView.class,//
                root.get("id"),//
                root.get("executionDate"),//
                root.get("originAccount").get("name"),//
                root.get("originAccount").get("id"),//
                root.get("destinationAccount").get("name"),//
                root.get("destinationAccount").get("id"),//
                root.get("amount"),
                root.get("description"),
                root.get("relatedParty").get("name"),//
                root.get("relatedParty").get("id")//
        ));

        TypedQuery<TransactionView> typedQuery = em.createQuery(criteriaQuery);
        List<TransactionView> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Override
    public List<TransactionView> getAll() {
        return searchTransactions(new TransactionSearchCriteria());
    }
}

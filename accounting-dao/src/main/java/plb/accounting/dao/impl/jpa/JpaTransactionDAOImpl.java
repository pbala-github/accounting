package plb.accounting.dao.impl.jpa;

import org.springframework.util.StringUtils;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

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
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class JpaTransactionDAOImpl extends JPAEntityDaoImpl implements TransactionDAO {

    @Override
    public List<Transaction> searchTransactions(TransactionSearchCriteria criteria) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = builder.createQuery(Transaction.class);
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
            conditions.add(builder.in(root.get("originAccount").<Long>get("id")).in(criteria.getOriginAccountIds()));
        }

        if (!conditions.isEmpty())
            criteriaQuery.where(builder.and(conditions.toArray(new Predicate[0])));

        criteriaQuery.select(builder.construct(Transaction.class));

        TypedQuery<Transaction> typedQuery = em.createQuery(criteriaQuery);
        List<Transaction> resultList = typedQuery.getResultList();

        return resultList;
    }
}

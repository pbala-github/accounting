package plb.accounting.dao.impl.jpa;

import org.springframework.util.StringUtils;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.EntityDAO;
import plb.accounting.model.Account;
import plb.accounting.model.BaseEntity;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import javax.inject.Inject;
import javax.persistence.EntityManager;
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
 * Date: 3/8/13 4:15 PM
 */
public class JPAEntityDaoImpl implements EntityDAO {

    @Inject
    private EntityManager entityManager;

    @Override
    public <T extends BaseEntity> T findById(Class<T> clazz, long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends BaseEntity> T saveOrUpdate(T obj) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends BaseEntity> void delete(Class<T> clazz, long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T extends BaseEntity> List<T> getAll(Class<T> clazz) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

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

        criteria.select(builder.construct(Account.class));

        TypedQuery<Account> typedQuery = entityManager.createQuery(criteria);
        return typedQuery.getResultList();
    }

    @Override
    public List<Transaction> searchTransactions(TransactionSearchCriteria criteria) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = builder.createQuery(Transaction.class);
        Root<Transaction> root = criteriaQuery.from(Transaction.class);

        List<Predicate> conditions = new ArrayList<Predicate>();

        if(StringUtils.hasText(criteria.getOrgVat()))
            conditions.add(builder.equal(root.<ExternalParty>get("relatedParty").<String>get("vat"),criteria.getOrgVat()));

        if(criteria.getAmountFrom() != null)
            conditions.add(builder.greaterThanOrEqualTo(root.<BigDecimal>get("amount"),criteria.getAmountFrom()));

        if (criteria.getAmountTo() != null)
            conditions.add(builder.lessThanOrEqualTo(root.<BigDecimal>get("amount"), criteria.getAmountTo()));

        if(StringUtils.hasText(criteria.getDescription()))
            conditions.add(builder.like(root.<String>get("description"),criteria.getDescription()));

        if(criteria.getDestinationAccountIds() != null && !criteria.getDestinationAccountIds().isEmpty())
            //TODO
            ;
        
        if(criteria.getExecutionDateFrom() != null)
            conditions.add(builder.greaterThanOrEqualTo(root.<Date>get("executionDate"),criteria.getExecutionDateFrom()));
        
        if(criteria.getExecutionDateTo() != null)
            conditions.add(builder.lessThanOrEqualTo(root.<Date>get("executionDate"),criteria.getExecutionDateTo()));
        
        if(StringUtils.hasText(criteria.getOrgName()))
            conditions.add(builder.like(root.get("relatedParty").<String>get("name"),criteria.getOrgName()));
        
        if(criteria.getOriginAccountIds() != null && !criteria.getOriginAccountIds().isEmpty())
            //TODO
            ;


        if(!conditions.isEmpty())
            criteriaQuery.where(builder.and(conditions.toArray(new Predicate[0])));

        criteriaQuery.select(builder.construct(Transaction.class));

        TypedQuery<Transaction> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Transaction> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Override
    public List<ExternalParty> searchExternalParties(ExternalPartySearchCriteria criteria) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExternalParty> criteriaQuery = builder.createQuery(ExternalParty.class);
        Root<ExternalParty> root = criteriaQuery.from(ExternalParty.class);

        List<Predicate> conditions = new ArrayList<Predicate>();

        if(!conditions.isEmpty())
            criteriaQuery.where(builder.and(conditions.toArray(new Predicate[0])));

        criteriaQuery.select(builder.construct(ExternalParty.class));

        TypedQuery<ExternalParty> typedQuery = entityManager.createQuery(criteriaQuery);
        List<ExternalParty> resultList = typedQuery.getResultList();

        return resultList;
    }

}

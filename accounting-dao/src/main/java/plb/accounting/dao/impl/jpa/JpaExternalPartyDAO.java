package plb.accounting.dao.impl.jpa;

import org.springframework.util.StringUtils;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.model.ExternalParty;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * User: pbala
 * Date: 3/11/13 2:16 PM
 */
@Stateless
@Local(ExternalPartyDAO.class)
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class JpaExternalPartyDAO extends JPAEntityDao implements ExternalPartyDAO {


    @Override
    public List<ExternalParty> searchExternalParties(ExternalPartySearchCriteria criteria) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ExternalParty> criteriaQuery = builder.createQuery(ExternalParty.class);
        Root<ExternalParty> root = criteriaQuery.from(ExternalParty.class);

        List<Predicate> conditions = new ArrayList<Predicate>();

        if (StringUtils.hasText(criteria.getName()))
            conditions.add(builder.equal(root.get("name"), criteria.getName()));

        if (StringUtils.hasText(criteria.getVat()))
            conditions.add(builder.equal(root.get("vat"), criteria.getVat()));

        if (!conditions.isEmpty())
            criteriaQuery.where(builder.and(conditions.toArray(new Predicate[0])));

        if (!conditions.isEmpty())
            criteriaQuery.where(builder.and(conditions.toArray(new Predicate[0])));

        criteriaQuery.select(builder.construct(ExternalParty.class));

        TypedQuery<ExternalParty> typedQuery = em.createQuery(criteriaQuery);
        List<ExternalParty> resultList = typedQuery.getResultList();

        return resultList;
    }
}

package plb.accounting.dao.impl.jpa;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.ExternalParty_;
import plb.accounting.model.view.ExternalPartyView;

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

    /**
     * Search ExternalParties using advanced criteria
     *
     * @param criteria
     * @return
     */
    @Override
    public List<ExternalPartyView> searchExternalParties(ExternalPartySearchCriteria criteria) {
        Assert.notNull(criteria);
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ExternalPartyView> criteriaQuery = builder.createQuery(ExternalPartyView.class);
        Root<ExternalParty> root = criteriaQuery.from(ExternalParty.class);

        List<Predicate> conditions = new ArrayList<Predicate>();

        if (StringUtils.hasText(criteria.getName()))
            conditions.add(builder.like(builder.lower(root.<String>get(ExternalParty_.name)), "%" + criteria.getName().toLowerCase() + "%"));

        if (StringUtils.hasText(criteria.getVat()))
            conditions.add(builder.equal(root.get(ExternalParty_.vat), criteria.getVat()));

        if (!conditions.isEmpty())
            criteriaQuery.where(builder.and(conditions.toArray(new Predicate[0])));

        criteriaQuery.select(builder.construct(ExternalPartyView.class,//
                root.get(ExternalParty_.id),//
                root.get(ExternalParty_.name),//
                root.get(ExternalParty_.vat),//
                root.get(ExternalParty_.description)//
        ));

        TypedQuery<ExternalPartyView> typedQuery = em.createQuery(criteriaQuery);
        List<ExternalPartyView> resultList = typedQuery.getResultList();

        return resultList;
    }

    @Override
    public List<ExternalPartyView> getAll() {
        return searchExternalParties(new ExternalPartySearchCriteria());
    }
}

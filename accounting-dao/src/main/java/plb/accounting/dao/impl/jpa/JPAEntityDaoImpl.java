package plb.accounting.dao.impl.jpa;

import plb.accounting.dao.EntityDAO;
import plb.accounting.model.BaseEntity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * User: pbala
 * Date: 3/8/13 4:15 PM
 */

public abstract class JPAEntityDaoImpl implements EntityDAO {

    @Inject
    protected EntityManager em;

    @Override
    public <T extends BaseEntity> T findById(Class<T> clazz, long id) {
        return em.find(clazz, id);
    }

    @Override
    public <T extends BaseEntity> T saveOrUpdate(T obj) {
        if (obj.getId() != null && em.find(obj.getClass(), obj.getId()) != null) {
            obj = em.merge(obj);
        } else {
            em.persist(obj);
            em.flush();
            em.refresh(obj);
        }

        return obj;
    }

    @Override
    public <T extends BaseEntity> void delete(Class<T> clazz, long id) {
        Object reference = em.getReference(clazz, id);
        em.remove(reference);
    }

    @Override
    public <T extends BaseEntity> List<T> getAll(Class<T> clazz) {
        CriteriaQuery<T> criteriaQuery = em.getCriteriaBuilder().createQuery(clazz);
        Root<T> root = criteriaQuery.from(clazz);
        return em.createQuery(criteriaQuery).getResultList();
    }

}

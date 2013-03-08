package plb.accounting.dao.impl.jpa;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: pbala
 * Date: 3/8/13 3:29 PM
 */
public class Resources {

    @Produces
    @PersistenceContext
    private EntityManager entityManager;

}

package plb.accounting.dao.impl.jpa;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class holds all the resources used by the application.
 * These resources may be environment-specific.
 *
 * User: pbala
 * Date: 3/8/13 3:29 PM
 */
public class Resources {

    @Produces
    @PersistenceContext(unitName = "accountingPU")
    private EntityManager entityManager;

}

package plb.accounting.dao.impl.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import plb.accounting.dao.impl.Logging;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class holds all the resources used by the application.
 * These resources may be environment-specific.
 *
 * User: pbala
 * Date: 3/8/13 3:29 PM
 */
@Alternative
public class Resources {

    @Produces
    @PersistenceContext(unitName = "accountingPU")
    private EntityManager entityManager;

    @Produces
    @Logging
    private Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getBean().getClass());
    }

}

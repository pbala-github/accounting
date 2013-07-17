package plb.accounting.dao.test;

import org.slf4j.LoggerFactory;
import plb.accounting.dao.impl.Logging;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * User: pbala
 * Date: 3/13/13 10:21 AM
 */
@Alternative
public class JpaDaoResources {

    @Produces
    @PersistenceContext(unitName = "accountingTestMySqlPUJTA")
    private EntityManager entityManager;

    @Produces
    @Logging
    private org.slf4j.Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getBean().getClass());
    }

}

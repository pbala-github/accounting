package plb.accounting.dao.impl.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * User: pbala
 * Date: 2/27/13 4:42 PM
 */
public abstract class JPABaseDAO {

    @Inject
    protected EntityManager entityManager;
}

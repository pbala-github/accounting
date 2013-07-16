package plb.accounting.dao.test;

import org.junit.Before;
import plb.accounting.dao.EntityDAO;

import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * User: pbala
 * Date: 10/31/12 2:58 PM
 */
public abstract class AbstractDAOTest<D extends EntityDAO> {

    @Inject
    protected EntityManager em;

    @Inject
    DataBootstrap dataBootstrap;

    @Before
    public void setUp() {
        em.getTransaction().begin();
        dataBootstrap.bootstrap();
        em.getTransaction().commit();
        em.clear();
    }

    @Before
    public void tearDown() {
        em.getTransaction().begin();
        dataBootstrap.cleanup();
        em.getTransaction().commit();
    }

    public abstract void persist();

    public abstract void findById();

    public abstract void delete();

    public abstract void update();

    public abstract void getAll();

    public abstract void searchByCriteria();

    protected abstract D getDAO();

    protected static void bootstrap() {

    }
}

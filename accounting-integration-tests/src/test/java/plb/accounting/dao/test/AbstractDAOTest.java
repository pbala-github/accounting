package plb.accounting.dao.test;

import org.junit.After;
import org.junit.Before;
import plb.accounting.dao.EntityDAO;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.*;

/**
 * User: pbala
 * Date: 10/31/12 2:58 PM
 */
public abstract class AbstractDAOTest<D extends EntityDAO> {

    @Inject
    protected EntityManager em;

    @Inject
    DataBootstrap dataBootstrap;

    @Inject
    UserTransaction userTransaction;

    @Before
    public void setUp() throws SystemException, RollbackException, HeuristicRollbackException, HeuristicMixedException, NotSupportedException {
        beginTransaction();
        dataBootstrap.bootstrap();
        commitTransaction();
        em.clear();
    }

    @After
    public void tearDown() throws SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        beginTransaction();
        dataBootstrap.cleanup();
        commitTransaction();
        em.clear();
    }

    protected void beginTransaction() {
        try {
            userTransaction.begin();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    protected void commitTransaction() {
        try {
            userTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
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

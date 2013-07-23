package plb.accounting.services.test;

import org.junit.After;
import org.junit.Before;
import plb.accounting.dao.test.DataBootstrap;
import plb.accounting.services.AccountingService;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.transaction.*;

/**
 * User: pbala
 * Date: 3/17/13 11:32 PM
 */
public abstract class AbstractServiceTest {

    @Inject
    DataBootstrap dataBootstrap;

    @EJB
    AccountingService service;

    @Inject
    UserTransaction userTransaction;


    @Before
    public void setUp() throws SystemException, RollbackException, HeuristicRollbackException, HeuristicMixedException, NotSupportedException {
        beginTransaction();
        dataBootstrap.bootstrap();
        commitTransaction();
    }

    @After
    public void tearDown() throws SystemException, NotSupportedException, RollbackException, HeuristicRollbackException, HeuristicMixedException {
        beginTransaction();
        dataBootstrap.cleanup();
        commitTransaction();
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

}

package plb.accounting.dao.dbunit;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AccountDaoTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    public static final String dataSetFile = "./src/test/resources/dataset.xml";


    /**
     * Initialize EntityManager before any test begins
     */
    @BeforeClass
    public static void initEntityManager() {
        emf = Persistence.createEntityManagerFactory(Constants.PERSISTENCE_UNIT);
        em = emf.createEntityManager();
    }

    /**
     * Close EntityMangerFactory/EntityManager after all tests are completed
     */
    @AfterClass
    public static void closeEntityManger() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    /**
     * Begin a new transaction and reset data before every test method
     */
    @Before
    public void initTransaction() throws Exception {
        TestUtils.seedData(em, dataSetFile);
    }

}

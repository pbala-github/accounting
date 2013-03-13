package plb.accounting.dao.test;

import org.junit.BeforeClass;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.dao.impl.jpa.JpaExternalPartyDAO;

/**
 * User: pbala
 * Date: 3/13/13 10:49 AM
 */
public class JpaExternalPartyDAOTest extends AbstractExternalPartyDAOTest {

    private static ExternalPartyDAO dao;

    @BeforeClass
    public static void setUp() {
        dao = JpaDaoUtil.advanceDao(new JpaExternalPartyDAO(), ExternalPartyDAO.class);
        DataBootstrap.bootstrap(dao);
    }

    @Override
    protected ExternalPartyDAO getDAO() {
        return dao;
    }
}

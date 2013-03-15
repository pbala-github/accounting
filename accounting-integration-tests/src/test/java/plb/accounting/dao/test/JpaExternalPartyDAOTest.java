package plb.accounting.dao.test;

import com.googlecode.jeeunit.JeeunitRunner;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.dao.impl.jpa.JpaExternalPartyDAO;

import javax.inject.Inject;

/**
 * User: pbala
 * Date: 3/13/13 10:49 AM
 */
@RunWith(JeeunitRunner.class)
public class JpaExternalPartyDAOTest extends AbstractExternalPartyDAOTest {

    @Inject
    @Transactional
    private ExternalPartyDAO dao;


    @Override
    protected ExternalPartyDAO getDAO() {
        return dao;
    }
}

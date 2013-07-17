package plb.accounting.dao.test;

import com.googlecode.jeeunit.JeeunitRunner;
import org.junit.runner.RunWith;
import plb.accounting.dao.ExternalPartyDAO;

import javax.ejb.EJB;
import javax.inject.Inject;

/**
 * User: pbala
 * Date: 3/13/13 10:49 AM
 */
@RunWith(JeeunitRunner.class)
public class JpaExternalPartyDAOTest extends AbstractExternalPartyDAOTest {

    @EJB
    private ExternalPartyDAO externalPartyDAO;


    @Override
    protected ExternalPartyDAO getDAO() {
        return externalPartyDAO;
    }
}

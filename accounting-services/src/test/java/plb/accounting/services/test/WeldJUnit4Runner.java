package plb.accounting.services.test;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * User: pbala
 * Date: 4/4/13 11:26 AM
 */
public class WeldJUnit4Runner extends BlockJUnit4ClassRunner{
    /**
     * Creates a BlockJUnit4ClassRunner to run {@code klass}
     *
     * @throws org.junit.runners.model.InitializationError
     * if the test class is malformed.
     */

    private static WeldContainer container = createContainer();
    
    public WeldJUnit4Runner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected Object createTest() throws Exception {
        return container.instance().select(getTestClass().getJavaClass()).get();
    }


    private static WeldContainer createContainer() {
        Weld weld = new Weld();

        return weld.initialize();
    }
}

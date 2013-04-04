package plb.accounting.services.test;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.events.ContainerInitialized;
import plb.accounting.common.validation.AccountingValidator;
import plb.accounting.common.validation.IAccountingValidator;
import plb.accounting.common.validation.ValidationError;
import plb.accounting.common.validation.ValidationErrorList;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

/**
 * User: pbala
 * Date: 1/14/13 3:45 PM
 */
public abstract class BaseValidationTest {

    @Inject
    private IAccountingValidator validator;

//    static {
//        Weld weld = new Weld();
//        WeldContainer container = weld.initialize();
//        System.out.println("CDI Container Initialized: " + container);
//        validator = container.instance().select(IAccountingValidator.class).get();
//        System.out.println("Validator Retrieved: " + validator);
//    }

    protected IAccountingValidator getValidator() {
        return validator;
    }

    protected void printValidationErrors(ValidationErrorList errorList) {
        System.out.println("**********************************************");
        System.out.println("TOTAL ERRORS: " + errorList.getErrors().size());
        for (ValidationError error : errorList.getErrors()) {
            System.out.println("Error: " + error.getFieldPointer() + ": " + error.getMessageKey());

        }
        System.out.println("**********************************************");
    }

    public void printHello(@Observes ContainerInitialized event) {

        System.out.println("CDI Container initialized");

    }
}

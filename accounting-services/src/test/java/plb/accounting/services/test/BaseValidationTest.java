package plb.accounting.services.test;

import plb.accounting.common.validation.AccountingValidator;
import plb.accounting.common.validation.IAccountingValidator;
import plb.accounting.common.validation.ValidationError;
import plb.accounting.common.validation.ValidationErrorList;

/**
 * User: pbala
 * Date: 1/14/13 3:45 PM
 */
public abstract class BaseValidationTest {

    private static IAccountingValidator validator;

    static {
        validator = new AccountingValidator();
        ((AccountingValidator)validator).init();
    }

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


}

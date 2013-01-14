package plb.accounting.common.validation;

import java.util.List;

/**
 * User: pbala
 * Date: 1/14/13 9:54 AM
 */
public class ValidationException extends RuntimeException{

    /**
     *
     */
    private ValidationErrorList errorList;

    public ValidationException(ValidationErrorList errorList) {
        this.errorList = errorList;
    }

    public ValidationErrorList getErrorList() {
        return errorList;
    }
}

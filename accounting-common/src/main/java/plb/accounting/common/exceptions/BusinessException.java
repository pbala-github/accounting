package plb.accounting.common.exceptions;

import plb.accounting.common.validation.ValidationErrorList;

/**
 * User: pbala
 * Date: 1/14/13 9:54 AM
 */
public class BusinessException extends RuntimeException{

    /**
     *
     */
    private ValidationErrorList errorList;

    public BusinessException(ValidationErrorList errorList) {
        this.errorList = errorList;
    }

    public ValidationErrorList getErrorList() {
        return errorList;
    }
}

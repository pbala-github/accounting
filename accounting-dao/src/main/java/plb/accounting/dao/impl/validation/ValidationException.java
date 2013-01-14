package plb.accounting.dao.impl.validation;

import java.util.List;

/**
 * User: pbala
 * Date: 1/14/13 9:54 AM
 */
public class ValidationException extends RuntimeException{

    /**
     *
     */
    private List<ValidationError> validationErrors;

    /**
     *
     * @param validationErrors
     */
    public ValidationException(List<ValidationError> validationErrors) {
        this.validationErrors = validationErrors;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}

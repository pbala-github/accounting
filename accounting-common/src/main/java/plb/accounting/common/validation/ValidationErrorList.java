package plb.accounting.common.validation;

import java.io.Serializable;
import java.util.List;

/**
 * User: pbala
 * Date: 1/14/13 3:41 PM
 */
public class ValidationErrorList implements Serializable {

    private List<ValidationError> errors;

    public ValidationErrorList(List<ValidationError> errors) {
        this.errors = errors;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }
}

package plb.accounting.common.validation;

import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * User: pbala
 * Date: 1/14/13 9:36 AM
 */
public class AccountingValidator implements IAccountingValidator {

    @Inject
    private Validator validator;

    @Override
    public <T> ValidationErrorList validate(T obj, Class<?>... groups) {
        Set<ConstraintViolation<T>> violations = validator.validate(obj, groups);

        return ValidationErrorConverter.toValidationErrorList(violations);
    }

}

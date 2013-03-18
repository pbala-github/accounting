package plb.accounting.common.validation;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * User: pbala
 * Date: 1/14/13 9:36 AM
 */
public class AccountingValidator implements IAccountingValidator{

    private Validator validator;

    @Override
    public <T> ValidationErrorList validate(T obj, Class<?>...groups) {
        Set<ConstraintViolation<T>> violations = validator.validate(obj,groups);
        
        return ValidationErrorConverter.toValidationErrorList(violations);
    }

    @PostConstruct
    private void init() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }


}

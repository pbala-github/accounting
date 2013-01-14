package plb.accounting.common.validation;

import javax.validation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * User: pbala
 * Date: 1/14/13 9:36 AM
 */
public class AccountingValidator implements IAccountingValidator{

    private static IAccountingValidator instance;

    public static synchronized IAccountingValidator get(){

        if(instance == null)
            instance = new AccountingValidator();

        return instance;
    }

    private Validator validator;

    private AccountingValidator() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    @Override
    public <T> ValidationErrorList validate(T obj, Class<?>...groups) {
        Set<ConstraintViolation<T>> violations = validator.validate(obj,groups);
        
        return new ValidationErrorList(transformToValidationErrors(violations));
    }

    private <T> List<ValidationError> transformToValidationErrors(Set<ConstraintViolation<T>> violations) {
        List<ValidationError> validationErrors = new ArrayList<ValidationError>();
        
        for(ConstraintViolation violation : violations){
            for(Path.Node node : violation.getPropertyPath()){
                validationErrors.add(new ValidationError(node.getName(),
                        node.getIndex(),
                        violation.getConstraintDescriptor().getAnnotation().annotationType().getCanonicalName(),
                        violation.getInvalidValue(),
                        violation.getMessage()));
            }
        }
        
        return validationErrors; 
    }
}

package plb.accounting.common.validation;

import javax.validation.ConstraintViolation;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * User: pbala
 * Date: 1/15/13 9:19 AM
 */
public class ValidationErrorConverter {
    
    public static <T> ValidationErrorList toValidationErrorList(Set<ConstraintViolation<T>> constraintViolations){

        List<ValidationError> errors = new ArrayList<ValidationError>(constraintViolations.size());
        
        for(ConstraintViolation violation : constraintViolations){
            ValidationError error = toError(violation);
            errors.add(error);
        }
        
        return new ValidationErrorList(errors);
    }

    private static ValidationError toError(ConstraintViolation violation) {
        String fieldPointer = String.valueOf(violation.getPropertyPath());
        String messageKey =  removeBrackets(violation.getMessage());
        Serializable value = toSerializable(violation.getInvalidValue());

        return new ValidationError(fieldPointer,messageKey,value);
    }

    private static Serializable toSerializable(Object invalidValue) {
        if(invalidValue == null)
            return null;

        return invalidValue instanceof Serializable? (Serializable) invalidValue: MessageFormat.format("Class {0} is not serializable.",invalidValue.getClass().getCanonicalName());
    }

    private static String removeBrackets(String messageTemplate) {
        return messageTemplate.replace("{","").replace("}","");
    }
}

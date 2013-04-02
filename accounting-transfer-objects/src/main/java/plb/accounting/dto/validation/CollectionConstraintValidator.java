package plb.accounting.dto.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

/**
 * User: pbala
 * Date: 1/15/13 9:45 AM
 */
public class CollectionConstraintValidator implements ConstraintValidator<NotEmptyCollection, Collection<?>> {

    @Override
    public void initialize(NotEmptyCollection constraintAnnotation) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid(Collection<?> value, ConstraintValidatorContext context) {

        return !ValidationUtils.isEmptyCollection(value) &&
                !ValidationUtils.hasEmptyElements(value);

    }

}

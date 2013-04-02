package plb.accounting.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: pbala
 * Date: 1/15/13 9:38 AM
 */
@Target(value = {ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CollectionConstraintValidator.class)
@Documented
public @interface NotEmptyCollection {
    String message() default "{com.plb.accounting.NotEmptyCollection}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

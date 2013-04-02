package plb.accounting.dto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: pbala
 * Date: 1/14/13 2:30 PM
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountCreationConstraintValidator.class)
@Documented
public @interface AccountCreationConstraint {
    String message() default "{com.plb.accounting.acountTypeOrParent.required}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

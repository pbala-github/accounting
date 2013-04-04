package plb.accounting.services.impl.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: pbala
 * Date: 1/14/13 3:07 PM
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TransactionCreationConstraintValidator.class)
@Documented
public @interface TransactionCreationConstraint {
    String message() default "{account.transaction.balanceViolation}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
package plb.accounting.dto.validation;

import plb.accounting.dto.BaseAccountDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: pbala
 * Date: 1/14/13 2:31 PM
 */
public class AccountCreationConstraintValidator implements ConstraintValidator<AccountCreationConstraint, BaseAccountDTO> {

    @Override
    public void initialize(AccountCreationConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(BaseAccountDTO value, ConstraintValidatorContext context) {

        if (value.getType() == null) {
            return value.getParentAccount() != null && value.getParentAccount().getId() != null;
        } else if (value.getType() != null && value.getParentAccount() == null) {
            return true;
        } else {
            return false;
        }

    }
}

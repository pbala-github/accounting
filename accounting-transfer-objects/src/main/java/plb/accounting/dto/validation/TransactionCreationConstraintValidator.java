package plb.accounting.dto.validation;

import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.TransactionDTO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * User: pbala
 * Date: 1/14/13 3:08 PM
 */
public class TransactionCreationConstraintValidator implements ConstraintValidator<TransactionCreationConstraint,TransactionDTO>{

    @Override
    public void initialize(TransactionCreationConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(TransactionDTO value, ConstraintValidatorContext context) {
        AccountDTO srcAccount = (AccountDTO) value.getOriginAccount();

        return srcAccount.getCurrentBalance() != null && srcAccount.getCurrentBalance().subtract(value.getAmount()).compareTo(BigDecimal.ZERO) >= 0 ;
    }
}

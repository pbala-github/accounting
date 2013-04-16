package plb.accounting.services.impl.validation;

import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.services.AccountService;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * User: pbala
 * Date: 1/14/13 3:08 PM
 */
public class TransactionCreationConstraintValidator implements ConstraintValidator<TransactionCreationConstraint, TransactionDTO> {

    @Inject
    AccountService service;

    @Override
    public void initialize(TransactionCreationConstraint constraintAnnotation) {

    }

    @Override
    public boolean isValid(TransactionDTO transaction, ConstraintValidatorContext context) {

        boolean valid = true;

        DetailedAccountDTO oa = (transaction.getOriginAccount() == null || transaction.getOriginAccount().getId() == null) ? null : service.loadAccountById(transaction.getOriginAccount().getId());
        DetailedAccountDTO da = (transaction.getDestinationAccount() == null || transaction.getDestinationAccount().getId() == null) ? null : service.loadAccountById(transaction.getDestinationAccount().getId());

        context.disableDefaultConstraintViolation();
        if (oa == null) {
            context.buildConstraintViolationWithTemplate("{transaction.originAccount.Null}").addNode("originAccount").addConstraintViolation();
            valid = false;
        }
        if (da == null) {
            context.buildConstraintViolationWithTemplate("{transaction.destinationAccount.Null}").addNode("destinationAccount").addConstraintViolation();
            valid = false;
        }
        if (oa != null && !validBalance(transaction.getAmount(), oa.getCurrentBalance())) {
            context.buildConstraintViolationWithTemplate("{account.transaction.balanceViolation}").addConstraintViolation();
            valid = false;
        }


        return valid;
    }

    private boolean validBalance(BigDecimal requestedAmount, BigDecimal availableAmount) {
        return availableAmount.subtract(requestedAmount).compareTo(BigDecimal.ZERO) >= 0;
    }

}

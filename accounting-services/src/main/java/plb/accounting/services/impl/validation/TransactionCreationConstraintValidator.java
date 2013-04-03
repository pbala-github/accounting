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

        boolean valid = transaction.getOriginAccount().getId() != null;

        if (valid) {
            DetailedAccountDTO oa = service.loadAccountById(transaction.getOriginAccount().getId());
            DetailedAccountDTO da = service.loadAccountById(transaction.getDestinationAccount().getId());

            if (oa == null || da == null || !validBalance(transaction.getAmount(), oa.getCurrentBalance())) {
                valid = false;
            }

        }

        return valid;
    }

    private boolean validBalance(BigDecimal requestedAmount, BigDecimal availableAmount) {
        return availableAmount.subtract(requestedAmount).compareTo(BigDecimal.ZERO) >= 0;
    }
}

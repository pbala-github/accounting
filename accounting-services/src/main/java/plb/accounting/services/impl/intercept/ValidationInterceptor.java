package plb.accounting.services.impl.intercept;

import plb.accounting.common.exceptions.BusinessException;
import plb.accounting.common.validation.IAccountingValidator;
import plb.accounting.common.validation.ValidationError;
import plb.accounting.common.validation.ValidationErrorList;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.util.ArrayList;

/**
 * User: pbala
 * Date: 4/1/13 1:28 PM
 */
@Validate
@Interceptor
public class ValidationInterceptor {

    @Inject
    private IAccountingValidator validator;

    @AroundInvoke
    public Object manageTransaction(InvocationContext ctx) throws Exception {

        System.out.println("INTERCEPTING METHOD FOR VALIDATION");
        Object[] parameters = ctx.getParameters();
        ValidationErrorList errorList = new ValidationErrorList(new ArrayList<ValidationError>());

        for (Object parameter : parameters) {
            errorList.getErrors().addAll(validator.validate(parameter, new Class<?>[]{}).getErrors());
        }

        if (!errorList.getErrors().isEmpty()) {
            throw new BusinessException(errorList);
        }

        return ctx.proceed();
    }
}

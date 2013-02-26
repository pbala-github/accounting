package plb.accounting.common.validation;

/**
 * User: pbala
 * Date: 1/14/13 9:31 AM
 */
public interface IAccountingValidator {

    <T> ValidationErrorList validate(T obj, Class<?>...groups);
}

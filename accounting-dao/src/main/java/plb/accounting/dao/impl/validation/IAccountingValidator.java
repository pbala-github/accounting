package plb.accounting.dao.impl.validation;

import javax.validation.ConstraintViolation;
import java.util.List;

/**
 * User: pbala
 * Date: 1/14/13 9:31 AM
 */
public interface IAccountingValidator {

    <T> List<ValidationError> validate(T obj);
}

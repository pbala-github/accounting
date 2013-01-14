package plb.accounting.dao.impl.validation;

import javax.validation.Path;

/**
 * User: pbala
 * Date: 1/14/13 9:34 AM
 */
public class ValidationError {

    private String fieldName;

    private Integer index;

    private String validationId;

    private Object invalidValue;

    private String defaultMessage;

    public ValidationError(String fieldName, Integer index, String validationId, Object invalidValue, String defaultMessage) {
        this.fieldName = fieldName;
        this.index = index;
        this.validationId = validationId;
        this.invalidValue = invalidValue;
        this.defaultMessage = defaultMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Integer getIndex() {
        return index;
    }

    public String getValidationId() {
        return validationId;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }
}

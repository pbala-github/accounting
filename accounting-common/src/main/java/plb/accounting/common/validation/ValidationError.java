package plb.accounting.common.validation;

import java.io.Serializable;

/**
 * User: pbala
 * Date: 1/14/13 9:34 AM
 */
public class ValidationError implements Serializable{

    private String fieldPointer;

    private String messageKey;

    private Serializable invalidValue;

    public ValidationError(String fieldPointer, String messageKey, Serializable invalidValue) {
        this.fieldPointer = fieldPointer;
        this.messageKey = messageKey;
        this.invalidValue = invalidValue;
    }

    public String getFieldPointer() {
        return fieldPointer;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Serializable getInvalidValue() {
        return invalidValue;
    }
}

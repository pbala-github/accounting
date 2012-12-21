package plb.accounting.mvc.model;

import java.io.Serializable;

/**
 * User: pbala
 * Date: 12/21/12 4:06 PM
 */
public abstract class AbstractWM implements Serializable {

    private boolean readOnly;

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }
}

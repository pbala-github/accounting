package plb.accounting.web;

import plb.accounting.common.exceptions.BusinessException;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;

/**
 * User: pbala
 * Date: 4/10/13 9:00 AM
 */
public class ExceptionHandlingHelper {

    public static void populateErrors(Exception ex) {
        ServletRequest request = (ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        if (ex.getCause() instanceof BusinessException) {
            BusinessException be = (BusinessException) ex.getCause();
            request.setAttribute("errorsList", be.getErrorList());
        } else {
            throw new RuntimeException(ex);
        }
    }
}

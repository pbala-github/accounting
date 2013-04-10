package plb.accounting.web;

import javax.faces.context.FacesContext;

/**
 * User: pbala
 * Date: 4/10/13 12:00 PM
 */
public class WebFunctions {

    /**
     * Returns whether there are any messages in the JSF message context.
     *
     * @return
     */
    public static boolean hasAnyMessages() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getMessages().hasNext();
    }
}

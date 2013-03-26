package plb.accounting.web;

import org.springframework.util.StringUtils;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;

/**
 * User: pbala
 * Date: 3/26/13 8:56 AM
 */
public final class WebHelper {

    public static String getRequestParameter(ServletRequest request,String parameterName) {
        return request.getParameter(parameterName);
    }

    public static Long getLongRequestParameter(ServletRequest request,String parameterName) {
        String parameter = getRequestParameter(request,parameterName);

        if (StringUtils.hasText(parameter)) {
            return new Long(parameter);
        } else {
            return null;
        }
    }
}

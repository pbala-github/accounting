package plb.accounting.web;

import plb.accounting.web.qualifiers.RequestParam;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;

/**
 * User: pbala
 * Date: 3/26/13 9:45 AM
 */
public class RequestParameterExtractor {

    @Produces
    public ServletRequest getRequest() {
        return (ServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    @Produces
    @RequestParam()
    public Long getTransactionId(ServletRequest request,InjectionPoint ip) {
        return WebHelper.getLongRequestParameter(request,ip.getAnnotated().getAnnotation(RequestParam.class).value());
    }
}

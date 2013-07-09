package plb.accounting.web.jsf;

import javax.enterprise.context.spi.Context;
import javax.enterprise.context.spi.Contextual;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @author: pbala
 */
public class ViewContext implements Context {

    @Override
    public Class<? extends Annotation> getScope() {
        return ViewScoped.class;
    }

    @Override
    public <T> T get(Contextual<T> contextual, CreationalContext<T> creationalContext) {
        Bean bean = (Bean) contextual;
        Map viewMap = getViewMap();
        if (viewMap.containsKey(bean.getName())) {
            return (T) viewMap.get(bean.getName());
        } else {
            T t = (T) bean.create(creationalContext);
            viewMap.put(bean.getName(), t);
            return t;
        }
    }

    @Override
    public <T> T get(Contextual<T> contextual) {
        Bean bean = (Bean) contextual;
        Map viewMap = getViewMap();
        if (viewMap.containsKey(bean.getName())) {
            return (T) viewMap.get(bean.getName());
        } else {
            return null;
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }

    /**
     * Return the current view map from JSF context
     *
     * @return
     */
    private Map getViewMap() {
        FacesContext fctx = FacesContext.getCurrentInstance();
        UIViewRoot viewRoot = fctx.getViewRoot();
        return viewRoot.getViewMap(true);
    }
}

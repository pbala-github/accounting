package plb.accounting.web.jsf;

import org.apache.commons.lang.StringUtils;

import javax.faces.view.facelets.ResourceResolver;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.URL;

/**
 * User: pbala
 * Date: 3/18/13 6:27 PM
 */
@WebListener
public class CustomResourceResolver extends ResourceResolver implements ServletContextListener{

    public static final String VIEWS_FOLDER_PARAM_NAME = "accounting.VIEWS_FOLDER";

    private static String DEFAULT_VIEWS_FOLDER = "/WEB-INF/views/pages/";

    private String viewsFolder = DEFAULT_VIEWS_FOLDER;

    private ResourceResolver delegate;

    //needed for cdi engine
    public CustomResourceResolver() {
    }

    public CustomResourceResolver(ResourceResolver delegate) {
        this.delegate = delegate;
    }

    @Override
    public URL resolveUrl(String s) {
        URL url = delegate.resolveUrl(s);
        if (url == null) {
            url = delegate.resolveUrl(viewsFolder + s);
        }

        return url;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        String vf = servletContext.getInitParameter(VIEWS_FOLDER_PARAM_NAME);
        if (StringUtils.isNotBlank(vf)) {
            viewsFolder = vf.endsWith("/") ? vf : vf + "/";
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

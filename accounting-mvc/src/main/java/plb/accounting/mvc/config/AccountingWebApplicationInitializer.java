package plb.accounting.mvc.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * This class is automatically detected by Servlet container and thus the Spring context is initialized.
 *
 * User: pbala
 * Date: 11/19/12 10:05 PM
 */
public class AccountingWebApplicationInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {
        registerDispatcherServlet(servletContext);
    }

    private void registerDispatcherServlet(ServletContext servletContext) {

        WebApplicationContext dispatcherContext = createContext(ApplicationConfigurationContext.class);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(dispatcherContext);
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",dispatcherServlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/app/*");
    }

    private WebApplicationContext createContext(final Class<?> ... annotatedClasses){
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(annotatedClasses);
        context.getEnvironment().setActiveProfiles("mock");
        return context;
    }
}

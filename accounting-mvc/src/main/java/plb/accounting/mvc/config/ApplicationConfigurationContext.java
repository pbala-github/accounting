package plb.accounting.mvc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.faces.mvc.JsfView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.webflow.definition.registry.FlowDefinitionRegistry;
import org.springframework.webflow.executor.FlowExecutor;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;
import org.springframework.webflow.mvc.servlet.FlowHandlerMapping;

/**
 * User: pbala
 * Date: 11/16/12 11:14 PM
 */
@Configuration
@EnableWebMvc
@ImportResource("classpath:/spring/webflow-config.xml")
public class ApplicationConfigurationContext {

    @Autowired
    private FlowExecutor flowExecutor;
    @Autowired
    private FlowDefinitionRegistry flowDefinitionRegistry;

    @Bean
    public ViewResolver jsfViewResolver(){
        UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
        viewResolver.setPrefix("WEB-INF/views/");
        viewResolver.setSuffix(".xhtml");
        viewResolver.setViewClass(JsfView.class);
        return viewResolver;
    }

    @Bean
    public FlowHandlerAdapter flowHandlerAdapter(){
        FlowHandlerAdapter flowHandlerAdapter = new FlowHandlerAdapter();
        flowHandlerAdapter.setFlowExecutor(flowExecutor);
        return flowHandlerAdapter;
    }

    @Bean
    public FlowHandlerMapping flowHandlerMapping(){
        FlowHandlerMapping flowHandlerMapping = new FlowHandlerMapping();
        flowHandlerMapping.setFlowRegistry(flowDefinitionRegistry);
        return flowHandlerMapping;
    }

    @Configuration
    @Profile(value = "production")
    public static class ProductionApplicationConfiguration{

    }

    @Configuration
    @Profile(value = "mock")
    @ComponentScan(basePackages = {"plb.accounting.mvc.mock"})
    public static class MockApplicationConfiguration{

    }
}

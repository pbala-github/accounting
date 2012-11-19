package plb.accounting.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * User: pbala
 * Date: 11/16/12 11:14 PM
 */
@Configuration
@EnableWebMvc
public class ApplicationConfigurationContext {


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

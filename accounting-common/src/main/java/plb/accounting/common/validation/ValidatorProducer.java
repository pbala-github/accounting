package plb.accounting.common.validation;

import org.springframework.util.Assert;
import plb.accounting.common.injection.Accounting;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.validation.*;
import java.io.InputStream;

/**
 * User: pbala
 * Date: 4/4/13 10:38 AM
 */
public class ValidatorProducer {

    private Validator validator;

    @Inject
    private BeanManager beanManager;

    @PostConstruct
    private void postConstruct() {
        Assert.notNull(beanManager);
        Configuration<?> configuration = Validation.byDefaultProvider().configure();
        configuration.addMapping(getMappingStream());
        ConstraintValidatorFactory constraintValidatorFactory = new CDIConstraintValidatorFactory(beanManager);
        ValidatorFactory validatorFactory = configuration.constraintValidatorFactory(constraintValidatorFactory).buildValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Accounting
    @Produces
    public Validator getValidator() {
        return validator;
    }

    /**
     * @return
     */
    public InputStream getMappingStream() {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream("accounting-validation.xml");
    }

}

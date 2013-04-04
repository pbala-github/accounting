package plb.accounting.common.validation;

import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import javax.validation.*;

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
        ConstraintValidatorFactory constraintValidatorFactory = new CDIConstraintValidatorFactory(beanManager);
        ValidatorFactory validatorFactory = configuration.constraintValidatorFactory(constraintValidatorFactory).buildValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Produces
    public Validator getValidator() {
        return validator;
    }

}

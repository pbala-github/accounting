package plb.accounting.common.validation;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;
import javax.validation.Validation;
import java.util.Set;

/**
 * User: pbala
 * Date: 4/4/13 10:42 AM
 */
public class CDIConstraintValidatorFactory implements ConstraintValidatorFactory {

    private BeanManager beanManager;
    private ConstraintValidatorFactory delegate;

    public CDIConstraintValidatorFactory(BeanManager beanManager) {
        this.beanManager = beanManager;
        delegate = Validation.byDefaultProvider().configure().getDefaultConstraintValidatorFactory();
    }

    @Override
    public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> tClass) {
        Set<Bean<?>> beans = beanManager.getBeans(tClass);

        if (!beans.isEmpty()) {
            Bean<? extends Object> bean = beanManager.resolve(beans);
            CreationalContext<? extends Object> creationalContext = beanManager.createCreationalContext(bean);
            return (T) beanManager.getReference(bean, tClass, creationalContext);
        }

        return delegate.getInstance(tClass);
    }

}

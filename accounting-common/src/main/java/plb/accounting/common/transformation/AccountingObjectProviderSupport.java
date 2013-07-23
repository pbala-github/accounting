package plb.accounting.common.transformation;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

/**
 * @author: pbala
 */
public abstract class AccountingObjectProviderSupport<T> implements AccountingObjectProvider<T> {

    @Override
    public Class<?>[] getDestinationTypes() {
        return new Class<?>[]{(Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]};
    }
}

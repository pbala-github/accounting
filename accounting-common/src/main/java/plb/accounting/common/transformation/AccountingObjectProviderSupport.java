package plb.accounting.common.transformation;

import java.lang.reflect.ParameterizedType;

/**
 * @author: pbala
 */
public abstract class AccountingObjectProviderSupport<T> implements AccountingObjectProvider<T> {

    @Override
    public Class<T> getDestinationType() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}

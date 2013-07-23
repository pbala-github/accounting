package plb.accounting.common.transformation;

import org.modelmapper.Provider;

/**
 * @author: pbala
 */
public interface AccountingObjectProvider<T> extends Provider<T> {

    Class<?>[] getSourceTypes();

    Class<?>[] getDestinationTypes();
}

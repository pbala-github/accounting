package plb.accounting.common.transformation;

import java.util.Collection;
import java.util.List;

/**
 * User: pbala
 * Date: 11/6/12 9:58 AM
 */
public interface ITransformationService {

    <T> T transform(Object srcObject, Class<T> targetClass);

    <T> List<T> transform(Collection<?> srcCollection, Class<T> targetClass);
}

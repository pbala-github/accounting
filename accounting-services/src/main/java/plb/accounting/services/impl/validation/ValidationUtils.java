package plb.accounting.services.impl.validation;

import java.util.Collection;

/**
 * User: pbala
 * Date: 1/15/13 9:46 AM
 */
public class ValidationUtils {

    public static boolean isEmptyCollection(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * @param value
     * @return
     */
    public static boolean hasEmptyElements(Collection<?> value) {

        for (Object o : value) {
            if (isEmptyObject(o))
                return true;
        }

        return false;
    }

    /**
     * @param o
     * @return
     */
    private static boolean isEmptyObject(Object o) {
        //TODO simplistic implementation
        return null == o;
    }
}

package plb.accounting.common.utils;

/**
 * @author: pbala
 */
public class Assert {

    public static void notNull(Object obj) {
        org.springframework.util.Assert.notNull(obj);
    }

    public static void isTrue(boolean expression) {
        org.springframework.util.Assert.isTrue(expression);
    }
}

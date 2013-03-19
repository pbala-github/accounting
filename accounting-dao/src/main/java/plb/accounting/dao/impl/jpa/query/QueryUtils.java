package plb.accounting.dao.impl.jpa.query;

import org.springframework.util.Assert;

/**
 * User: pbala
 * Date: 3/19/13 10:07 PM
 */
public final class QueryUtils {

    /**
     * Create an identifier for the given field
     *
     * @param field
     * @return
     */
    public static String createIdentifier(String field) {
        return field.toLowerCase() + field.length();
    }

    /**
     * @param s1
     * @param s2
     * @param counter
     * @return
     */
    public static String createIdentifier(String s1, String s2, int counter) {
        return new StringBuilder().append(s1).append(s2).append(counter).toString();
    }

    /**
     * Check whether the given field is nested
     *
     * @param field
     * @return true if nested
     */
    public static boolean isNestedProperty(String field) {
        return field.contains(".");
    }

    /**
     * Extract all sub-paths of a given (full-path) field
     *
     * @param field
     * @return
     */
    public static String[] extractFieldPaths(String field) {
        Assert.notNull(field);
        return field.split("\\.");
    }

    /**
     * @param identifier
     * @param field
     * @param operator
     * @param counter
     * @return
     */
    public static String createExpression(String identifier, String field, Operator operator, int counter) {
        Assert.notNull(identifier);
        StringBuilder sb = new StringBuilder("");

        sb.append(identifier).append(".");
        sb.append(operator.deflate(field, QueryUtils.createIdentifier(identifier, field, counter)));

        return sb.toString();
    }
}

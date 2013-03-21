package plb.accounting.dao.impl.jpa.query;

import org.springframework.util.Assert;

/**
 * User: pbala
 * Date: 3/19/13 6:01 PM
 */
public class Operator {

    /**
     *
     */
    public static final Operator EQUALS = new Operator("=");
    /**
     *
     */
    public static final Operator LESS_THAN = new Operator("<");
    /**
     *
     */
    public static final Operator GREATER_THAN = new Operator(">");
    /**
     *
     */
    public static final Operator IN = new Operator("IN");
    /**
     *
     */
    public static final Operator LIKE = new Operator("like");

    /**
     *
     */
    private String operand;

    protected Operator(String operand) {
        this.operand = operand;
    }

    public String deflate(String parameterName, String parameterAlias) {
        Assert.notNull(parameterName);
        Assert.notNull(parameterAlias);

        return new StringBuilder(parameterName).append(" ").append(getOperand()).append(" :").append(parameterAlias).toString();
    }

    public String getOperand() {
        return operand;
    }
}

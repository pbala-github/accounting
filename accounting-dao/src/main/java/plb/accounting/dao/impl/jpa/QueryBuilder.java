package plb.accounting.dao.impl.jpa;

import org.springframework.util.Assert;
import plb.accounting.common.paging.PaginationInfo;
import plb.accounting.model.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import java.util.*;

/**
 * User: pbala
 * Date: 3/13/13 2:51 PM
 */
public class QueryBuilder {

    public enum OperatorsEnum {
        EQUALS("="), LESS_THAN("<"), GREATER_THAN(">"), IN("IN");

        private String operator;

        OperatorsEnum(String operator) {
            this.operator = operator;
        }

        public String operator() {
            return this.operator;
        }

        @Override
        public String toString() {
            return operator();
        }
    }

    /**
     * Every QueryBuilder instance is associated with a single class. The final query is executed against this class.
     */
    private Class classToQuery;

    private LinkedHashMap<String, Set<Criteria>> criteriaMap = new LinkedHashMap<String, Set<Criteria>>();

    private QueryBuilder() {
    }

    /**
     * Factory method. Initiate a query builder for a specific class.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends BaseEntity> QueryBuilder forClass(Class<T> clazz) {
        QueryBuilder qb = new QueryBuilder();
        qb.classToQuery = clazz;
        return qb;
    }

    /**
     * Add an equality and criteria.
     *
     * @param field
     * @param value
     * @return
     */
    public QueryBuilder and(String field, Object value) {
        return and(field, value, OperatorsEnum.EQUALS);
    }

    /**
     * Add a greater than criteria
     *
     * @param field
     * @param value
     * @return
     */
    public QueryBuilder greaterThan(String field, Object value) {
        return and(field, value, OperatorsEnum.GREATER_THAN);
    }

    /**
     * Add a less than criteria.
     *
     * @param field
     * @param value
     * @return
     */
    public QueryBuilder lessThan(String field, Object value) {
        return and(field, value, OperatorsEnum.LESS_THAN);
    }

    /**
     * Criteria that filters values from a given set
     * @param field
     * @param set
     * @return
     */
    public QueryBuilder in(String field, Set<?> set) {
        return and(field, set, OperatorsEnum.IN);
    }

    /**
     * Create a criteria for the given field, value and operator and add it to the criteria map.
     *
     * @param field
     * @param value
     * @param operator
     * @return
     */
    private QueryBuilder and(String field, Object value, OperatorsEnum operator) {
        addCriteria(new Criteria(field, value, operator));
        return this;
    }

    /**
     * Build a Query object. Set its paging details too.
     *
     * @param em
     * @param paginationInfo
     * @return
     */
    public Query build(EntityManager em, PaginationInfo paginationInfo) {
        String queryString = constructQueryString();
        System.out.format("\n\nQuery String: %s\n\n", queryString);
        Query namedQuery = em.createQuery(queryString);
        setQueryParameters(namedQuery);

        //pagination info
        namedQuery.setFirstResult(paginationInfo.getOffset());
        namedQuery.setMaxResults(paginationInfo.getPageSize());

        return namedQuery;
    }

    /**
     * @param c
     */
    private void addCriteria(Criteria c) {
        if (!criteriaMap.containsKey(c.field))
            criteriaMap.put(c.field, new HashSet<Criteria>());

        criteriaMap.get(c.field).add(c);
    }

    /**
     * Construct the query string
     *
     * @return
     */
    private String constructQueryString() {
        String selectClause = buildSelectClause();
        String fromClause = buildFromClause();
        String whereClause = buildWhereClause();

        return new StringBuilder(selectClause).append(" ").append(fromClause).append(" ").append(whereClause).toString();
    }

    /**
     * Build the where clause of the query string
     *
     * @return
     */
    private String buildWhereClause() {
        final StringBuilder sb = new StringBuilder("");
        final String classIdentifier = createIdentifier(classToQuery.getSimpleName());

        if (!criteriaMap.isEmpty()) {
            sb.append("where ");
        }

        iterate(new VisitorSupport() {
            @Override
            void visitCriteria(Map.Entry<String, Set<Criteria>> entry, Criteria criteria, int counter) {
                if (isNestedProperty(criteria.field)) {
                    String[] fieldPaths = criteria.field.split("\\.");
                    sb.append(createExpression(createIdentifier(fieldPaths[0]), fieldPaths[1], criteria.operator, counter)).append(" and ");
                } else
                    sb.append(createExpression(classIdentifier, criteria.field, criteria.operator, counter)).append(" and ");
            }
        });

        if (sb.indexOf("and") > -1) {
            sb.replace(sb.lastIndexOf("and"), sb.length() - 1, "");
        }

        return sb.toString();
    }

    /**
     * Build the from clause of the query string
     *
     * @return
     */
    private String buildFromClause() {
        final String identifier = createIdentifier(classToQuery.getSimpleName());
        final StringBuilder sb = new StringBuilder("from ");
        sb.append(classToQuery.getSimpleName()).append(" ");
        sb.append(identifier).append(" ");

        iterate(new VisitorSupport() {
            @Override
            void visitCriteria(Map.Entry<String, Set<Criteria>> entry, Criteria criteria, int counter) {
                if (isNestedProperty(criteria.field)) {
                    String[] fieldPaths = criteria.field.split("\\.");
                    sb.append(",").append("IN(").append(identifier).append(".").append(fieldPaths[0]).append(") ").append(createIdentifier(fieldPaths[0]));
                }
            }
        });

        return sb.toString();
    }

    /**
     * Build the select clause of the query string
     *
     * @return
     */
    private String buildSelectClause() {
        String identifier = createIdentifier(classToQuery.getSimpleName());
        StringBuilder sb = new StringBuilder("select ");
        sb.append(identifier);

        return sb.toString();
    }

    /**
     * @param field
     * @return
     */
    private String createIdentifier(String field) {
        return field.toLowerCase() + field.length();
    }

    /**
     * @param s1
     * @param s2
     * @param counter
     * @return
     */
    private String createIdentifier(String s1, String s2, int counter) {
        return new StringBuilder().append(s1).append(s2).append(counter).toString();
    }

    /**
     * Check whether the given field is nested
     *
     * @param field
     * @return true if nested
     */
    private boolean isNestedProperty(String field) {
        return field.contains(".");
    }

    /**
     * @param identifier
     * @param field
     * @param operator
     * @param counter
     * @return
     */
    private String createExpression(String identifier, String field, OperatorsEnum operator, int counter) {
        Assert.notNull(identifier);
        StringBuilder sb = new StringBuilder("");

        sb.append(identifier).append(".");
        sb.append(field).append(" ").append(operator).append(" :").append(createIdentifier(identifier, field, counter));

        return sb.toString();
    }


    private void setQueryParameters(final Query query) {
        iterate(new VisitorSupport() {
            @Override
            void visitCriteria(Map.Entry<String, Set<Criteria>> entry, Criteria criteria, int counter) {
                String identifier;
                if (isNestedProperty(criteria.field)) {
                    String[] split = criteria.field.split("\\.");
                    identifier = createIdentifier(createIdentifier(split[0]), split[1], counter);
                } else {
                    identifier = createIdentifier(createIdentifier(classToQuery.getSimpleName()), criteria.field, counter);
                }

                if (Date.class.isAssignableFrom(criteria.value.getClass())) {
                    query.setParameter(identifier, (Date) criteria.value, TemporalType.DATE);
                } else
                    query.setParameter(identifier, criteria.value);
            }
        });
    }

    /**
     * Template method to iterate over the criteria map.
     *
     * @param vs
     */
    private void iterate(VisitorSupport vs) {

        for (Map.Entry<String, Set<Criteria>> entry : criteriaMap.entrySet()) {
            int c = 0;
            for (Criteria criteria : entry.getValue()) {
                vs.visitCriteria(entry, criteria, c++);
            }
        }
    }

    class Criteria {
        String field;
        Object value;
        OperatorsEnum operator;

        Criteria(String field, Object value, OperatorsEnum operator) {
            this.field = field;
            this.value = value;
            this.operator = operator;
        }
    }

    class VisitorSupport {
        void visitCriteria(Map.Entry<String, Set<Criteria>> entry, Criteria criteria, int counter) {
        }
    }
}

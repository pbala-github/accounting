package plb.accounting.dao.impl.jpa.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(getClass());

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
        return and(field, value, Operator.EQUALS);
    }

    /**
     * Add a greater than criteria
     *
     * @param field
     * @param value
     * @return
     */
    public QueryBuilder greaterThan(String field, Object value) {
        return and(field, value, Operator.GREATER_THAN);
    }

    /**
     * Add a less than criteria.
     *
     * @param field
     * @param value
     * @return
     */
    public QueryBuilder lessThan(String field, Object value) {
        return and(field, value, Operator.LESS_THAN);
    }

    /**
     * Criteria that filters values from a given set
     *
     * @param field
     * @param set
     * @return
     */
    public QueryBuilder in(String field, Set<?> set) {
        return and(field, set, Operator.IN);
    }

    /**
     * @param field
     * @return
     */
    public QueryBuilder nill(String field) {
        return and(field, null, Operator.NULL);
    }

    /**
     * Create a criteria for the given field, value and operator and add it to the criteria map.
     *
     * @param field
     * @param value
     * @param operator
     * @return
     */
    private QueryBuilder and(String field, Object value, Operator operator) {
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
        logger.debug(String.format("\n\nQuery String: %s\n\n", queryString));
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
        final String classIdentifier = QueryUtils.createIdentifier(classToQuery.getSimpleName());

        if (!criteriaMap.isEmpty()) {
            sb.append("where ");
        }

        QueryUtils.iterate(criteriaMap, new QueryUtils.VisitorSupport() {
            @Override
            void visitCriteria(Map.Entry<String, Set<Criteria>> entry, Criteria criteria, int counter) {
                if (QueryUtils.isNestedProperty(criteria.field)) {
                    String[] fieldPaths = QueryUtils.extractFieldPaths(criteria.field);
                    sb.append(QueryUtils.createExpression(QueryUtils.createIdentifier(fieldPaths[0]), fieldPaths[1], criteria.operator, counter)).append(" and ");
                } else
                    sb.append(QueryUtils.createExpression(classIdentifier, criteria.field, criteria.operator, counter)).append(" and ");
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
        final String identifier = QueryUtils.createIdentifier(classToQuery.getSimpleName());
        final StringBuilder sb = new StringBuilder("from ");
        sb.append(classToQuery.getSimpleName()).append(" ");
        sb.append(identifier).append(" ");

        QueryUtils.iterate(criteriaMap, new QueryUtils.VisitorSupport() {
            @Override
            void visitCriteria(Map.Entry<String, Set<Criteria>> entry, Criteria criteria, int counter) {
                if (QueryUtils.isNestedProperty(criteria.field)) {
                    String[] fieldPaths = QueryUtils.extractFieldPaths(criteria.field);
                    sb.append(" ").append("JOIN ").append(identifier).append(".").append(fieldPaths[0]).append(" ").append(QueryUtils.createIdentifier(fieldPaths[0]));
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
        String identifier = QueryUtils.createIdentifier(classToQuery.getSimpleName());
        StringBuilder sb = new StringBuilder("select ");
        sb.append(identifier);

        return sb.toString();
    }

    private void setQueryParameters(final Query query) {
        QueryUtils.iterate(criteriaMap, new QueryUtils.VisitorSupport() {
            @Override
            void visitCriteria(Map.Entry<String, Set<Criteria>> entry, Criteria criteria, int counter) {
                String identifier;
                if (QueryUtils.isNestedProperty(criteria.field)) {
                    String[] split = QueryUtils.extractFieldPaths(criteria.field);
                    identifier = QueryUtils.createIdentifier(QueryUtils.createIdentifier(split[0]), split[1], counter);
                } else {
                    identifier = QueryUtils.createIdentifier(QueryUtils.createIdentifier(classToQuery.getSimpleName()), criteria.field, counter);
                }

                if (criteria.value != null)
                    if (Date.class.isAssignableFrom(criteria.value.getClass())) {
                        query.setParameter(identifier, (Date) criteria.value, TemporalType.DATE);
                    } else
                        query.setParameter(identifier, criteria.value);
            }
        });
    }

    static class Criteria {
        String field;
        Object value;
        Operator operator;

        Criteria(String field, Object value, Operator operator) {
            this.field = field;
            this.value = value;
            this.operator = operator;
        }
    }

}

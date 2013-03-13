package plb.accounting.dao.impl.jpa;

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
        EQUALS("="), LESS_THAN("<"), GREATER_THAN(">");

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

    private Class clazzToQuery;

    private LinkedHashMap<String, Set<Criteria>> criteriaMap = new LinkedHashMap<String, Set<Criteria>>();

    private QueryBuilder() {

    }

    public static <T extends BaseEntity> QueryBuilder forClass(Class<T> clazz) {
        QueryBuilder qb = new QueryBuilder();
        qb.clazzToQuery = clazz;
        return qb;
    }

    public QueryBuilder and(String field, Object value) {
        return and(field, value, OperatorsEnum.EQUALS);
    }

    public QueryBuilder and(String field, Object value, OperatorsEnum operator) {
        addCriteria(new Criteria(field, value, operator));
        return this;
    }

    private void addCriteria(Criteria c) {
        if (!criteriaMap.containsKey(c))
            criteriaMap.put(c.field, new HashSet<Criteria>());

        criteriaMap.get(c.field).add(c);
    }

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

    private String constructQueryString() {
        StringBuilder sb = new StringBuilder("from ");
        sb.append(clazzToQuery.getSimpleName()).append(" ");

        if (!criteriaMap.isEmpty()) {
            sb.append("where ");
        }


        for (Map.Entry<String, Set<Criteria>> entry : criteriaMap.entrySet()) {
            int c = 0;
            for (Criteria criteria : entry.getValue()) {
                sb.append(entry.getKey()).append(criteria.operator).append(":").append(entry.getKey()).append(c++).append(" and ");
            }
        }

        if (sb.indexOf("and") > -1) {
            sb.replace(sb.lastIndexOf("and"), sb.length() - 1, "");
        }

        return sb.toString();
    }

    private void setQueryParameters(Query query) {
        for (Map.Entry<String, Set<Criteria>> entry : criteriaMap.entrySet()) {
            int c = 0;
            for (Criteria criteria : entry.getValue()) {
                if (Date.class.isAssignableFrom(criteria.value.getClass())) {
                    query.setParameter(entry.getKey() + (c++), (Date) criteria.value, TemporalType.DATE);
                } else
                    query.setParameter(entry.getKey() + (c++), criteria.value);
            }
        }

    }

    static class Criteria{
        String field;
        Object value;
        OperatorsEnum operator;

        Criteria(String field, Object value, OperatorsEnum operator) {
            this.field = field;
            this.value = value;
            this.operator = operator;
        }
    }
}

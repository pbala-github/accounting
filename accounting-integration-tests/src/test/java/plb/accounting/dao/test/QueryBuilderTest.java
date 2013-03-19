package plb.accounting.dao.test;

import org.junit.BeforeClass;
import org.junit.Test;
import plb.accounting.common.paging.PaginationInfo;
import plb.accounting.dao.impl.jpa.query.QueryBuilder;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;

/**
 * User: pbala
 * Date: 3/13/13 3:24 PM
 */
public class QueryBuilderTest {

    private static EntityManager em;

    @BeforeClass
    public static void setUp() {
        em = Persistence.createEntityManagerFactory("testAccountingPU").createEntityManager();
    }

    @Test
    public void testQuery() {
        String queryString = "select a from Account a, IN(a.parentAccount) pa where pa.id = :paid";

        Query namedQuery = em.createQuery(queryString);
        namedQuery.setParameter("paid", new Long(23));
    }

    @Test
    public void testAccountSearchCriteriaBuilder() {

        QueryBuilder qb = QueryBuilder.forClass(Account.class);
        qb.and("name", "accountName")
                .and("type", AccountTypeEnum.INCOME)
                .lessThan("currentBalance", BigDecimal.TEN)
                .greaterThan("currentBalance", BigDecimal.ONE)
                .and("parentAccount.id", new Long(2));

        Query query = qb.build(em, new PaginationInfo());
    }

    @Test
    public void testTransactionSearchCriteriaBuilder() {
        QueryBuilder qb = QueryBuilder.forClass(Transaction.class);
        qb.and("description", "description")
                .in("destinationAccount.id", new HashSet<Object>(Arrays.asList(1l, 2l, 3l, 4l)));

        qb.build(em, new PaginationInfo());
    }
}

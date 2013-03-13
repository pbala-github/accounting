package plb.accounting.dao.test;

import org.junit.BeforeClass;
import org.junit.Test;
import plb.accounting.dao.impl.jpa.QueryBuilder;
import plb.accounting.dto.AccountTypeEnum;
import plb.accounting.model.Account;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;

/**
 * User: pbala
 * Date: 3/13/13 3:24 PM
 */
public class QueryBuilderTest {

    private static EntityManager em;

    @BeforeClass
    public static void setUp(){
        em = Persistence.createEntityManagerFactory("testAccountingPU").createEntityManager();
    }

    @Test
    public void testAccountSearchCriteriaBuilder() {

        QueryBuilder qb = QueryBuilder.forClass(Account.class);
        qb.and("name", "accountName")
                /*.and("type", AccountTypeEnum.INCOME)*/
                .and("currentBalance", BigDecimal.TEN, QueryBuilder.OperatorsEnum.LESS_THAN)
                .and("currentBalance", BigDecimal.ONE, QueryBuilder.OperatorsEnum.GREATER_THAN)
                .and("parentAccount", 2222l);

        Query query = qb.build(em);
    }
}

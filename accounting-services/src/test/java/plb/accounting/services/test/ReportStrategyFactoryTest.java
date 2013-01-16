package plb.accounting.services.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import plb.accounting.dto.reporting.*;
import plb.accounting.services.impl.reporting.*;

/**
 * Test that the right report strategy is retrieved
 * using the report criteria
 *
 * User: pbala
 * Date: 1/16/13 2:24 PM
 */
public class ReportStrategyFactoryTest {

    private static IReportManager reportManager;

    @BeforeClass
    public static void setup(){
        reportManager = new ReportManager();
    }

    @Test
    public void balanceReportStrategy(){
        BalanceReportCriteria criteria = new BalanceReportCriteria();
        criteria.setGroupBy(IGroupingReportCriteria.GroupType.ACCOUNT);
        IReportStrategy<BalanceReportResult, BalanceReportCriteria> strategy = reportManager.getReportStrategy(criteria);

        Assert.assertEquals(AccountBalanceReportStrategy.class,strategy.getClass());

        criteria.setGroupBy(IGroupingReportCriteria.GroupType.PERIOD);
        strategy = reportManager.getReportStrategy(criteria);

        Assert.assertEquals(PeriodBalanceReportStrategy.class,strategy.getClass());
    }

    @Test
    public void incomeReportStrategy(){
        IncomeReportCriteria criteria = new IncomeReportCriteria();
        criteria.setGroupBy(IGroupingReportCriteria.GroupType.ACCOUNT);
        IReportStrategy<BalanceReportResult, BalanceReportCriteria> strategy = reportManager.getReportStrategy(criteria);

        Assert.assertEquals(AccountIncomeReportStrategy.class,strategy.getClass());

        criteria.setGroupBy(IGroupingReportCriteria.GroupType.PERIOD);
        strategy = reportManager.getReportStrategy(criteria);

        Assert.assertEquals(PeriodIncomeReportStrategy.class,strategy.getClass());
    }

    @Test
    public void outcomeReportStrategy(){
        OutcomeReportCriteria criteria = new OutcomeReportCriteria();
        criteria.setGroupBy(IGroupingReportCriteria.GroupType.ACCOUNT);
        IReportStrategy<BalanceReportResult, BalanceReportCriteria> strategy = reportManager.getReportStrategy(criteria);

        Assert.assertEquals(AccountOutcomeReportStrategy.class,strategy.getClass());

        criteria.setGroupBy(IGroupingReportCriteria.GroupType.PERIOD);
        strategy = reportManager.getReportStrategy(criteria);

        Assert.assertEquals(PeriodOutcomeReportStrategy.class,strategy.getClass());
    }

}

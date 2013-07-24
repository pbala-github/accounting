package plb.accounting.services.impl.reporting;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import plb.accounting.dto.reporting.*;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.view.TransactionView;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author: pbala
 */
public class TestReportStrategy {

    private static List<TransactionView> transactionViews;
    private static BigDecimal totalOutcome;
    private static BigDecimal totalIncome;

    @BeforeClass
    public static void globalSetUp() {
        transactionViews = SampleReportData.getSampleTransactionViews();
        totalOutcome = getTotalOutcome();
        totalIncome = getTotalIncome();
    }

    @Test
    public void testPeriodBalanceReport() {
        PeriodBalanceReportStrategy reportStrategy = new PeriodBalanceReportStrategy();
        reportStrategy.groupStrategy = new TransactionPeriodGroupStrategy();
        BalanceReportResult report = reportStrategy.createReport(new BalanceReportCriteria(), transactionViews);
        Assert.assertEquals(totalOutcome.doubleValue(), report.getTotalOutcome(), 0);
        Assert.assertEquals(totalIncome.doubleValue(), report.getTotalIncome(), 0);
    }

    @Test
    public void testPeriodIncomeReport() {
        PeriodIncomeReportStrategy reportStrategy = new PeriodIncomeReportStrategy();
        reportStrategy.groupStrategy = new TransactionPeriodGroupStrategy();
        IncomeReportResult report = reportStrategy.createReport(new IncomeReportCriteria(), transactionViews);
        Assert.assertEquals(totalIncome.doubleValue(), report.getTotalIncome(), 0);
    }

    @Test
    public void testPeriodOutcomeReport() {
        PeriodOutcomeReportStrategy reportStrategy = new PeriodOutcomeReportStrategy();
        reportStrategy.groupStrategy = new TransactionPeriodGroupStrategy();
        OutcomeReportResult report = reportStrategy.createReport(new OutcomeReportCriteria(), transactionViews);
        Assert.assertEquals(totalOutcome.doubleValue(), report.getTotalOutcome(), 0);
    }

    @Test
    public void testAccountBalanceReport() {
        AccountBalanceReportStrategy reportStrategy = new AccountBalanceReportStrategy();
        reportStrategy.groupStrategy = new TransactionAccountGroupStrategy();
        BalanceReportResult report = reportStrategy.createReport(new BalanceReportCriteria(), transactionViews);
        Assert.assertEquals(totalOutcome.doubleValue(), report.getTotalOutcome(), 0);
        Assert.assertEquals(totalIncome.doubleValue(), report.getTotalIncome(), 0);
    }

    @Test
    public void testAccountIncomeReport() {
        AccountIncomeReportStrategy reportStrategy = new AccountIncomeReportStrategy();
        reportStrategy.groupStrategy = new TransactionAccountGroupStrategy();
        IncomeReportResult report = reportStrategy.createReport(new IncomeReportCriteria(), transactionViews);
        Assert.assertEquals(totalIncome.doubleValue(), report.getTotalIncome(), 0);
    }

    @Test
    public void testAccountOutcomeReport() {
        AccountOutcomeReportStrategy reportStrategy = new AccountOutcomeReportStrategy();
        reportStrategy.groupStrategy = new TransactionAccountGroupStrategy();
        OutcomeReportResult report = reportStrategy.createReport(new OutcomeReportCriteria(), transactionViews);
        Assert.assertEquals(totalOutcome.doubleValue(), report.getTotalOutcome(), 0);
    }

    private static BigDecimal getTotalOutcome() {
        BigDecimal total = BigDecimal.ZERO;
        for (TransactionView transactionView : transactionViews) {
            if (transactionView.getDestinationAccountType().equals(AccountTypeEnum.OUTCOME))
                total = total.add(transactionView.getAmount());
        }

        return total;
    }

    private static BigDecimal getTotalIncome() {
        BigDecimal total = BigDecimal.ZERO;
        for (TransactionView transactionView : transactionViews) {
            if (transactionView.getDestinationAccountType().equals(AccountTypeEnum.STORAGE))
                total = total.add(transactionView.getAmount());
        }

        return total;
    }
}

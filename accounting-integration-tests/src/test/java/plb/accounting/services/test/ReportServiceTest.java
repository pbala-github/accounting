package plb.accounting.services.test;

import com.googlecode.jeeunit.JeeunitRunner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import plb.accounting.dto.reporting.*;

/**
 * User: pbala
 * Date: 3/17/13 11:38 PM
 */
@RunWith(JeeunitRunner.class)
public class ReportServiceTest extends AbstractServiceTest {
    @Test
    public void createBalanceReport(){
        BalanceReportCriteria criteria = new BalanceReportCriteria();
        BalanceReportResult report = service.createBalanceReport(criteria);

        Assert.assertNotNull(report);
        Assert.assertNotNull(report.getReportCriteria());
    }

    @Test
    public void createOutcomeReport() {
        OutcomeReportCriteria criteria = new OutcomeReportCriteria();
        OutcomeReportResult report = service.createOutcomeReport(criteria);

        Assert.assertNotNull(report);
        Assert.assertNotNull(report.getReportCriteria());
    }

    @Test
    public void createIncomeReport() {
        IncomeReportCriteria criteria = new IncomeReportCriteria();
        IncomeReportResult report = service.createIncomeReport(criteria);

        Assert.assertNotNull(report);
        Assert.assertNotNull(report.getReportCriteria());
    }
}

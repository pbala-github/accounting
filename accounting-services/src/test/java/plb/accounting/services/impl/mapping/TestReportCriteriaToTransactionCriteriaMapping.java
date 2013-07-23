package plb.accounting.services.impl.mapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.reporting.IGroupingReportCriteria;
import plb.accounting.dto.reporting.IncomeReportCriteria;
import plb.accounting.dto.reporting.OutcomeReportCriteria;
import plb.accounting.services.test.WeldJUnit4Runner;

import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author: pbala
 */
@RunWith(WeldJUnit4Runner.class)
public class TestReportCriteriaToTransactionCriteriaMapping {

    @Inject
    private ITransformationService transformationService;

    @Test
    public void testMapping() {
        OutcomeReportCriteria outcomeReportCriteria = new OutcomeReportCriteria();
        Calendar calendar = Calendar.getInstance();
        outcomeReportCriteria.setEndDate(calendar.getTime());
        calendar.add(Calendar.MONTH, -1);
        outcomeReportCriteria.setStartDate(calendar.getTime());
        outcomeReportCriteria.setGroupBy(IGroupingReportCriteria.GroupType.ACCOUNT);
        outcomeReportCriteria.getIncludedAccountsIds().add(111l);

        TransactionSearchCriteria transactionSearchCriteria = transformationService.transform(outcomeReportCriteria, TransactionSearchCriteria.class);

        assertEquals(outcomeReportCriteria.getIncludedAccountsIds(), transactionSearchCriteria.getDestinationAccountIds());
        assertEquals(outcomeReportCriteria.getIncludedAccountsIds(), transactionSearchCriteria.getOriginAccountIds());
        assertEquals(outcomeReportCriteria.getEndDate(), transactionSearchCriteria.getExecutionDateTo());
        assertEquals(outcomeReportCriteria.getStartDate(), transactionSearchCriteria.getExecutionDateFrom());
    }
}

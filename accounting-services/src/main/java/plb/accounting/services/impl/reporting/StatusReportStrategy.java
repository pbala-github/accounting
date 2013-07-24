package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.ReportCriteria;
import plb.accounting.dto.reporting.StatusReportCriteria;
import plb.accounting.dto.reporting.StatusReportResult;
import plb.accounting.model.view.AccountView;

import java.util.List;

/**
 * User: pbala
 * Date: 3/22/13 4:25 PM
 */
public class StatusReportStrategy implements IReportStrategy<StatusReportResult, StatusReportCriteria> {

    @Override
    public StatusReportResult createReport(StatusReportCriteria reportCriteria, Object data) {
        List<AccountView> accounts = (List<AccountView>) data;
        StatusReportResult result = new StatusReportResult(reportCriteria);

        for (AccountView account : accounts) {
            result.addResultEntry(account.getName(), account.getDbId(), account.getCurrentBalance().doubleValue());
        }

        return result;
    }

    @Override
    public boolean supports(ReportCriteria reportCriteria) {
        return reportCriteria != null && StatusReportCriteria.class.isAssignableFrom(reportCriteria.getClass());
    }

}

package plb.accounting.services;

import plb.accounting.dto.reporting.*;

/**
 * User: pbala
 * Date: 11/7/12 12:30 PM
 */
public interface ReportService {

    BalanceReportResult createBalanceReport(BalanceReportCriteria criteria);

    OutcomeReportResult createOutcomeReport(OutcomeReportCriteria criteria);

    IncomeReportResult createIncomeReport(IncomeReportCriteria criteria);

}

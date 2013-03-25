package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.ReportCriteria;
import plb.accounting.dto.reporting.ReportResult;

/**
 * User: pbala
 * Date: 11/10/12 10:43 PM
 */
public interface IReportManager {

    <T extends ReportResult,E extends ReportCriteria> IReportStrategy<T,E> getReportStrategy(ReportCriteria reportCriteria);

    <T extends ReportResult,E extends ReportCriteria> T createReport(E criteria, Object data);
}

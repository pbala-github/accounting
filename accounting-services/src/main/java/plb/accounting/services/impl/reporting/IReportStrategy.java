package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.ReportCriteria;
import plb.accounting.dto.reporting.ReportResult;

/**
 * User: pbala
 * Date: 11/10/12 10:43 PM
 */
public interface IReportStrategy<T extends ReportResult, E extends ReportCriteria> {

    T createReport(E reportCriteria, Object data);

    boolean supports(ReportCriteria reportCriteria);
}

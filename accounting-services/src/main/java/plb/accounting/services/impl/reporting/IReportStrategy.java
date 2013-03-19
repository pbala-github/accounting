package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.IReportCriteria;
import plb.accounting.dto.reporting.IReportResult;

/**
 * User: pbala
 * Date: 11/10/12 10:43 PM
 */
public interface IReportStrategy<T extends IReportResult, E extends IReportCriteria> {

    T createReport(E reportCriteria, Object data);

    boolean supports(IReportCriteria reportCriteria);
}

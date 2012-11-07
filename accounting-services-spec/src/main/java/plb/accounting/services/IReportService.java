package plb.accounting.services;

import plb.accounting.dto.reporting.BalanceReportCriteria;
import plb.accounting.dto.reporting.BalanceReportResult;

/**
 * User: pbala
 * Date: 11/7/12 12:30 PM
 */
public interface IReportService {

    BalanceReportResult createBalanceReport(BalanceReportCriteria criteria);

}

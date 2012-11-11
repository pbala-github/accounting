package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.BalanceReportCriteria;
import plb.accounting.dto.reporting.IReportCriteria;

/**
 * User: pbala
 * Date: 11/10/12 11:09 PM
 */
public class ReportManager implements IReportManager{


    public IReportStrategy getReportStrategy(IReportCriteria reportCriteria) {
       throw new RuntimeException("A concrete implementation of IReportCriteria should be provided.");
    }


    /**
     *
     * @param reportCriteria
     * @return
     */
    public IReportStrategy getReportStrategy(BalanceReportCriteria reportCriteria) {
        if(BalanceReportCriteria.GroupType.ACCOUNT.equals(reportCriteria.getGroupBy()))
            return new AccountBalanceReportStrategy();
        else
            return new PeriodBalanceReportStrategy();

    }
}

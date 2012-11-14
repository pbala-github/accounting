package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.*;

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
    public IReportStrategy<BalanceReportResult,BalanceReportCriteria> getReportStrategy(BalanceReportCriteria reportCriteria) {
        if(BalanceReportCriteria.GroupType.ACCOUNT.equals(reportCriteria.getGroupBy()))
            return new AccountBalanceReportStrategy();
        else
            return new PeriodBalanceReportStrategy();

    }

    /**
     *
     * @param reportCriteria
     * @return
     */
    public IReportStrategy<OutcomeReportResult,OutcomeReportCriteria> getReportStrategy(OutcomeReportCriteria reportCriteria) {
        if(BalanceReportCriteria.GroupType.ACCOUNT.equals(reportCriteria.getGroupBy()))
            return new AccountOutcomeReportStrategy();
        else
            return new PeriodOutcomeReportStrategy();

    }

    /**
     *
     * @param reportCriteria
     * @return
     */
    public IReportStrategy<IncomeReportResult,IncomeReportCriteria> getReportStrategy(IncomeReportCriteria reportCriteria) {
        if(BalanceReportCriteria.GroupType.ACCOUNT.equals(reportCriteria.getGroupBy()))
            return new AccountIncomeReportStrategy();
        else
            return new PeriodIncomeReportStrategy();

    }
}

package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.*;

/**
 * User: pbala
 * Date: 11/10/12 11:09 PM
 */
public class ReportManager implements IReportManager{


    /**
     * Dispatch to the right overloaded method
     *
     * @param reportCriteria
     * @return
     */
    public <T extends IReportResult,E extends IReportCriteria> IReportStrategy<T,E> getReportStrategy(IReportCriteria reportCriteria) {
        if(reportCriteria instanceof BalanceReportCriteria)
            return (IReportStrategy<T, E>) this.getReportStrategy((BalanceReportCriteria)reportCriteria);
        else if(reportCriteria instanceof OutcomeReportCriteria)
            return (IReportStrategy<T, E>) this.getReportStrategy((OutcomeReportCriteria)reportCriteria);
        else if(reportCriteria instanceof IncomeReportCriteria)
            return (IReportStrategy<T, E>) this.getReportStrategy((IncomeReportCriteria)reportCriteria);

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

    @Override
    public <T extends IReportResult, E extends IReportCriteria> T createReport(E criteria, Object data) {

         IReportStrategy<T,E> reportStrategy = getReportStrategy(criteria);

        T result = reportStrategy.createReport(criteria,data);
        result.setReportCriteria(criteria);

        return result;
    }
}

package plb.accounting.dto.reporting;

import java.util.Date;

/**
 * User: pbala
 * Date: 11/13/12 4:36 PM
 */
public class IncomeReportResult extends BaseReportResult<IncomeReportCriteria> {

    /**
     *
     */
    private double totalIncome;

    /**
     * @param reportCriteria
     */
    public IncomeReportResult(IncomeReportCriteria reportCriteria) {
        super(reportCriteria);
    }

    /**
     * @param income
     * @param startDate
     * @param endDate
     * @return
     */
    public BaseReportResultEntry addResultEntry(double income, Date startDate, Date endDate) {
        return super.addResultEntry(income,0,startDate,endDate);
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }
}

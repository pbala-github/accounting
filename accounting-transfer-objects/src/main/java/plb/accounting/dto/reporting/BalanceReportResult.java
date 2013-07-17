package plb.accounting.dto.reporting;

/**
 * User: pbala
 * Date: 11/7/12 9:32 AM
 */
public class BalanceReportResult extends BaseReportResult<BalanceReportCriteria> {

    /**
     *
     */
    private double totalIncome;

    /**
     *
     */
    private double totalOutcome;

    /**
     * @param reportCriteria
     */
    public BalanceReportResult(BalanceReportCriteria reportCriteria) {
        super(reportCriteria);
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }

    public double getTotalOutcome() {
        return totalOutcome;
    }

    public void setTotalOutcome(double totalOutcome) {
        this.totalOutcome = totalOutcome;
    }
}

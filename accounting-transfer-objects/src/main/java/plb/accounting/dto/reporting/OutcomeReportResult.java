package plb.accounting.dto.reporting;

/**
 * User: pbala
 * Date: 11/13/12 4:20 PM
 */
public class OutcomeReportResult extends BaseReportResult<OutcomeReportCriteria> {

    /**
     *
     */
    private double totalOutcome;

    /**
     * @param reportCriteria
     */
    public OutcomeReportResult(OutcomeReportCriteria reportCriteria) {
        super(reportCriteria);
    }

    public double getTotalOutcome() {
        return totalOutcome;
    }

    public void setTotalOutcome(double totalOutcome) {
        this.totalOutcome = totalOutcome;
    }
}

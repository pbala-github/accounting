package plb.accounting.dto.reporting;

/**
 * User: pbala
 * Date: 11/13/12 4:36 PM
 */
public class IncomeReportResult extends BaseReportResult<IncomeReportCriteria>{

    /**
     *
     */
    private double totalIncome;


    public double getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(double totalIncome) {
        this.totalIncome = totalIncome;
    }
}

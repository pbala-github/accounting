package plb.accounting.dto.reporting;

/**
 * @author: pbala
 */
public abstract class BaseReportResultEntry {

    /**
     *
     */
    private Double income;

    /**
     *
     */
    private Double outcome;

    public BaseReportResultEntry(Double income, Double outcome) {
        this.income = income;
        this.outcome = outcome;
    }


    public Double getIncome() {
        return income;
    }

    public Double getOutcome() {
        return outcome;
    }

}


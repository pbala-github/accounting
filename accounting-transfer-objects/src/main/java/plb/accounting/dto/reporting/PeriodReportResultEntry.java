package plb.accounting.dto.reporting;

import java.util.Date;

/**
 * @author: pbala
 */
public class PeriodReportResultEntry extends BaseReportResultEntry {

    /**
     *
     */
    private Date startDate;

    /**
     *
     */
    private Date endDate;

    public PeriodReportResultEntry(Double income, Double outcome, Date startDate, Date endDate) {
        super(income, outcome);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}


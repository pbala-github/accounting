package plb.accounting.dto.reporting;

/**
 * User: pbala
 * Date: 11/7/12 9:32 AM
 */
public abstract class AbstractReportResult<T extends ReportCriteria> implements ReportResult<T> {

    /**
     *
     */
    private T reportCriteria;

    protected AbstractReportResult(T reportCriteria) {
        this.reportCriteria = reportCriteria;
    }

    public T getReportCriteria() {
        return reportCriteria;
    }

}

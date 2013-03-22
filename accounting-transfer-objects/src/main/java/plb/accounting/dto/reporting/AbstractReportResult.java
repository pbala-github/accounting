package plb.accounting.dto.reporting;

/**
 * User: pbala
 * Date: 11/7/12 9:32 AM
 */
public abstract class AbstractReportResult<T extends IReportCriteria> implements IReportResult<T> {

    /**
     *
     */
    private T reportCriteria;


    public T getReportCriteria() {
        return reportCriteria;
    }

    public void setReportCriteria(T reportCriteria) {
        this.reportCriteria = reportCriteria;
    }
}

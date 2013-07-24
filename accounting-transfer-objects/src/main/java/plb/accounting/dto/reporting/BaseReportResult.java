package plb.accounting.dto.reporting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: pbala
 * Date: 11/7/12 9:32 AM
 */
public abstract class BaseReportResult<T extends ReportCriteria> extends AbstractReportResult<T> {

    /**
     *
     */
    private List<BaseReportResultEntry> resultEntries = new ArrayList<BaseReportResultEntry>();

    /**
     * @param reportCriteria
     */
    protected BaseReportResult(T reportCriteria) {
        super(reportCriteria);
    }


    /**
     * @param income
     * @param outcome
     * @param startDate
     * @param endDate
     * @return
     */
    public BaseReportResultEntry addResultEntry(double income, double outcome, Date startDate, Date endDate) {
        BaseReportResultEntry resultEntry = new PeriodReportResultEntry(income, outcome, startDate, endDate);
        resultEntries.add(resultEntry);
        return resultEntry;
    }

    /**
     * @param income
     * @param outcome
     * @param accountDbId
     * @param accountName
     * @return
     */
    public BaseReportResultEntry addResultEntry(double income, double outcome, Long accountDbId, String accountName) {
        BaseReportResultEntry resultEntry = new AccountReportResultEntry(income, outcome, accountName, accountDbId);
        resultEntries.add(resultEntry);
        return resultEntry;
    }


    public List<BaseReportResultEntry> getResultEntries() {
        return resultEntries;
    }


}

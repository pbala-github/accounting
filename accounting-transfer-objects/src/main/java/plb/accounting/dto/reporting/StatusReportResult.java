package plb.accounting.dto.reporting;

/**
 * User: pbala
 * Date: 3/22/13 4:17 PM
 */
public class StatusReportResult extends BaseReportResult<StatusReportCriteria> {

    /**
     * @param reportCriteria
     */
    public StatusReportResult(StatusReportCriteria reportCriteria) {
        super(reportCriteria);
    }

    /**
     * @return
     */
    public BaseReportResultEntry addResultEntry(String accountName, Long accountDbId, double currentBalance) {
        BaseReportResultEntry resultEntry = new AccountReportResultEntry(accountName, accountDbId, currentBalance);
        getResultEntries().add(resultEntry);
        return resultEntry;
    }


}

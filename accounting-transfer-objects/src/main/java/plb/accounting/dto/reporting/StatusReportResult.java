package plb.accounting.dto.reporting;

import plb.accounting.dto.AccountDTO;

/**
 * User: pbala
 * Date: 3/22/13 4:17 PM
 */
public class StatusReportResult extends BaseReportResult<StatusReportCriteria> {

    /**
     * @param reportCriteria
     */
    protected StatusReportResult(StatusReportCriteria reportCriteria) {
        super(reportCriteria);
    }

    /**
     * @param account
     * @return
     */
    public BaseReportResultEntry addResultEntry(AccountDTO account) {
        BaseReportResultEntry resultEntry = new BaseReportResultEntry(Double.valueOf(0), Double.valueOf(0), account);
        getResultEntries().add(resultEntry);
        return resultEntry;
    }


}

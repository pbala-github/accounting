package plb.accounting.dto.reporting;

import plb.accounting.dto.AccountDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * User: pbala
 * Date: 3/22/13 4:17 PM
 */
public class StatusReportResult extends AbstractReportResult<StatusReportCriteria> {


    /**
     *
     */
    private List<StatusReportResultEntry> resultEntries = new ArrayList<StatusReportResultEntry>();

    /**
     * @param account
     * @return
     */
    public StatusReportResultEntry addResultEntry(AccountDTO account) {
        StatusReportResultEntry resultEntry = new StatusReportResultEntry(account);
        resultEntries.add(resultEntry);
        return resultEntry;
    }


    public List<StatusReportResultEntry> getResultEntries() {
        return resultEntries;
    }

    public class StatusReportResultEntry {

        /**
         *
         */
        private AccountDTO account;

        public StatusReportResultEntry(AccountDTO account) {
            this.account = account;
        }

        public AccountDTO getAccount() {
            return account;
        }
    }

}

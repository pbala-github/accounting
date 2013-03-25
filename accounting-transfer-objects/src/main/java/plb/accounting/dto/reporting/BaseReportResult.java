package plb.accounting.dto.reporting;

import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.BaseAccountInfoDTO;

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
     * @param income
     * @param outcome
     * @param startDate
     * @param endDate
     * @return
     */
    public BaseReportResultEntry addResultEntry(double income, double outcome, Date startDate, Date endDate) {
        BaseReportResultEntry resultEntry = new BaseReportResultEntry(income, outcome, startDate, endDate);
        resultEntries.add(resultEntry);
        return resultEntry;
    }

    /**
     * @param income
     * @param outcome
     * @param account
     * @return
     */
    public BaseReportResultEntry addResultEntry(double income, double outcome, AccountDTO account) {
        BaseReportResultEntry resultEntry = new BaseReportResultEntry(income, outcome, account);
        resultEntries.add(resultEntry);
        return resultEntry;
    }


    public List<BaseReportResultEntry> getResultEntries() {
        return resultEntries;
    }

    public class BaseReportResultEntry {

        /**
         *
         */
        private Double income;

        /**
         *
         */
        private Double outcome;

        /**
         *
         */
        private BaseAccountInfoDTO account;

        /**
         *
         */
        private Date startDate;

        /**
         *
         */
        private Date endDate;


        public BaseReportResultEntry(Double income, Double outcome, Date startDate, Date endDate) {
            this.income = income;
            this.outcome = outcome;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public BaseReportResultEntry(Double income, Double outcome, BaseAccountInfoDTO account) {
            this.income = income;
            this.outcome = outcome;
            this.account = account;
        }

        public BaseReportResultEntry(Double income, Double outcome, BaseAccountInfoDTO account, Date startDate, Date endDate) {
            this.income = income;
            this.outcome = outcome;
            this.account = account;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Double getIncome() {
            return income;
        }

        public Double getOutcome() {
            return outcome;
        }

        public BaseAccountInfoDTO getAccount() {
            return account;
        }

        public Date getStartDate() {
            return startDate;
        }

        public Date getEndDate() {
            return endDate;
        }
    }

}

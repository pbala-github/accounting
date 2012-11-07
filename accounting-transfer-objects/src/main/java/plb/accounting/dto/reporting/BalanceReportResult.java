package plb.accounting.dto.reporting;

import plb.accounting.dto.BaseAccountDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: pbala
 * Date: 11/7/12 9:32 AM
 */
public class BalanceReportResult {

    /**
     * 
     */
    private BalanceReportCriteria criteria;

    /**
     *
     */
    private List<BalanceReportResultEntry> resultEntries = new ArrayList<BalanceReportResultEntry>();


    /**
     *
     * @param income
     * @param outcome
     * @param startDate
     * @param endDate
     * @return
     */
    public BalanceReportResultEntry addResultEntry(double income, double outcome,Date startDate, Date endDate){
        BalanceReportResultEntry resultEntry = new BalanceReportResultEntry(income,outcome,startDate,endDate);
        resultEntries.add(resultEntry);
        return resultEntry;
    }

    /**
     *
     * @param income
     * @param outcome
     * @param account
     * @return
     */
    public BalanceReportResultEntry addResultEntry(double income, double outcome,BaseAccountDTO account){
        BalanceReportResultEntry resultEntry = new BalanceReportResultEntry(income,outcome,account);
        resultEntries.add(resultEntry);
        return resultEntry;
    }

    public BalanceReportCriteria getCriteria() {
        return criteria;
    }

    public void setCriteria(BalanceReportCriteria criteria) {
        this.criteria = criteria;
    }

    public List<BalanceReportResultEntry> getResultEntries() {
        return resultEntries;
    }

    public class BalanceReportResultEntry{

        /**
         *
         */
        private Double income;

        /**
         *
         */
        private double outcome;

        /**
         *
         */
        private BaseAccountDTO account;

        /**
         *
         */
        private Date startDate;

        /**
         *
         */
        private Date endDate;


        public BalanceReportResultEntry(Double income, double outcome, Date startDate, Date endDate) {
            this.income = income;
            this.outcome = outcome;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public BalanceReportResultEntry(Double income, double outcome, BaseAccountDTO account) {
            this.income = income;
            this.outcome = outcome;
            this.account = account;
        }

        public BalanceReportResultEntry(Double income, double outcome, BaseAccountDTO account, Date startDate, Date endDate) {
            this.income = income;
            this.outcome = outcome;
            this.account = account;
            this.startDate = startDate;
            this.endDate = endDate;
        }

        public Double getIncome() {
            return income;
        }

        public double getOutcome() {
            return outcome;
        }

        public BaseAccountDTO getAccount() {
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

package plb.accounting.services.impl;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.reporting.*;
import plb.accounting.model.Transaction;
import plb.accounting.services.IReportService;
import plb.accounting.services.impl.reporting.IReportManager;
import plb.accounting.services.impl.reporting.IReportStrategy;

import java.util.List;

/**
 * User: pbala
 * Date: 11/7/12 12:31 PM
 */
public class ReportService extends BaseService implements IReportService{

    private IReportManager reportManager;

    @Override
    public BalanceReportResult createBalanceReport(BalanceReportCriteria criteria) {

        TransactionSearchCriteria transactionCriteria = new TransactionSearchCriteria();
        transactionCriteria.setExecutionDateFrom(criteria.getStartDate());
        transactionCriteria.setExecutionDateTo(criteria.getEndDate());
        transactionCriteria.setDestinationAccountIds(criteria.getIncludedAccountsIds());
        transactionCriteria.setOriginAccountIds(criteria.getIncludedAccountsIds());

        List<Transaction> transactions = accountingDAOFacade.searchTransactions(transactionCriteria);

        IReportStrategy<BalanceReportResult,BalanceReportCriteria> reportStrategy = reportManager.getReportStrategy(criteria);
        return reportStrategy.createReport(criteria, transactions);
    }

    @Override
    public OutcomeReportResult createOutcomeReport(OutcomeReportCriteria criteria) {

        TransactionSearchCriteria transactionCriteria = new TransactionSearchCriteria();
        transactionCriteria.setExecutionDateFrom(criteria.getStartDate());
        transactionCriteria.setExecutionDateTo(criteria.getEndDate());
        transactionCriteria.setDestinationAccountIds(criteria.getIncludedAccountsIds());

        List<Transaction> transactions = accountingDAOFacade.searchTransactions(transactionCriteria);

        IReportStrategy<OutcomeReportResult,OutcomeReportCriteria> reportStrategy = reportManager.getReportStrategy(criteria);
        return reportStrategy.createReport(criteria, transactions);
    }

    @Override
    public IncomeReportResult createIncomeReport(IncomeReportCriteria criteria) {
        TransactionSearchCriteria transactionCriteria = new TransactionSearchCriteria();
        transactionCriteria.setExecutionDateFrom(criteria.getStartDate());
        transactionCriteria.setExecutionDateTo(criteria.getEndDate());
        transactionCriteria.setOriginAccountIds(criteria.getIncludedAccountsIds());

        List<Transaction> transactions = accountingDAOFacade.searchTransactions(transactionCriteria);

        IReportStrategy<IncomeReportResult,IncomeReportCriteria> reportStrategy = reportManager.getReportStrategy(criteria);
        return reportStrategy.createReport(criteria, transactions);
    }

    public void setReportManager(IReportManager reportManager) {
        this.reportManager = reportManager;
    }
}

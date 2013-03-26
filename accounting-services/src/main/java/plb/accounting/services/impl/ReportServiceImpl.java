package plb.accounting.services.impl;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.AccountDAO;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.dto.reporting.*;
import plb.accounting.model.Account;
import plb.accounting.model.Transaction;
import plb.accounting.services.ReportService;
import plb.accounting.services.impl.reporting.IReportManager;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.List;

/**
 * User: pbala
 * Date: 11/7/12 12:31 PM
 */
public class ReportServiceImpl extends BaseService implements ReportService {

    @Inject
    private IReportManager reportManager;
    @EJB
    private TransactionDAO transactionDAO;
    @EJB
    private AccountDAO accountDAO;

    @Override
    public BalanceReportResult createBalanceReport(BalanceReportCriteria criteria) {

        TransactionSearchCriteria transactionCriteria = new TransactionSearchCriteria();
        transactionCriteria.setExecutionDateFrom(criteria.getStartDate());
        transactionCriteria.setExecutionDateTo(criteria.getEndDate());
        transactionCriteria.setDestinationAccountIds(criteria.getIncludedAccountsIds());
        transactionCriteria.setOriginAccountIds(criteria.getIncludedAccountsIds());

        List<Transaction> transactions = transactionDAO.searchTransactions(transactionCriteria);

        return reportManager.createReport(criteria, transactions);
    }

    @Override
    public OutcomeReportResult createOutcomeReport(OutcomeReportCriteria criteria) {

        TransactionSearchCriteria transactionCriteria = new TransactionSearchCriteria();
        transactionCriteria.setExecutionDateFrom(criteria.getStartDate());
        transactionCriteria.setExecutionDateTo(criteria.getEndDate());
        transactionCriteria.setDestinationAccountIds(criteria.getIncludedAccountsIds());

        List<Transaction> transactions = transactionDAO.searchTransactions(transactionCriteria);

        return reportManager.createReport(criteria, transactions);
    }

    @Override
    public IncomeReportResult createIncomeReport(IncomeReportCriteria criteria) {
        TransactionSearchCriteria transactionCriteria = new TransactionSearchCriteria();
        transactionCriteria.setExecutionDateFrom(criteria.getStartDate());
        transactionCriteria.setExecutionDateTo(criteria.getEndDate());
        transactionCriteria.setOriginAccountIds(criteria.getIncludedAccountsIds());

        List<Transaction> transactions = transactionDAO.searchTransactions(transactionCriteria);

        return reportManager.createReport(criteria, transactions);
    }

    @Override
    public StatusReportResult createStatusReport(StatusReportCriteria criteria) {
        AccountSearchCriteria accountSearchCriteria = new AccountSearchCriteria();
        accountSearchCriteria.setTopParentAccount(true);
        List<Account> accounts = accountDAO.searchAccounts(accountSearchCriteria);

        return reportManager.createReport(criteria, accounts);
    }
}

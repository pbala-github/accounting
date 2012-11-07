package plb.accounting.services.impl;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.reporting.BalanceReportCriteria;
import plb.accounting.dto.reporting.BalanceReportResult;
import plb.accounting.model.Transaction;
import plb.accounting.services.IReportService;

import java.util.List;

/**
 * User: pbala
 * Date: 11/7/12 12:31 PM
 */
public class ReportService extends BaseService implements IReportService{

    @Override
    public BalanceReportResult createBalanceReport(BalanceReportCriteria criteria) {

        TransactionSearchCriteria transactionCriteria = new TransactionSearchCriteria();
        transactionCriteria.setExecutionDateFrom(criteria.getStartDate());
        transactionCriteria.setExecutionDateTo(criteria.getEndDate());
        transactionCriteria.setDestinationAccountIds(criteria.getIncludedAccountsIds());
        transactionCriteria.setOriginAccountIds(criteria.getIncludedAccountsIds());

        List<Transaction> transactions = accountingDAOFacade.searchTransactions(transactionCriteria);

        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

}

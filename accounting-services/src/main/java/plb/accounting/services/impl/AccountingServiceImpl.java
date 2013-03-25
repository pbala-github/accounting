package plb.accounting.services.impl;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.*;
import plb.accounting.dto.reporting.*;
import plb.accounting.services.*;
import plb.accounting.services.TransactionService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.util.List;

/**
 * Entry point class (facade) for service components
 * <p/>
 * User: pbala
 * Date: 11/5/12 4:01 PM
 */
@Local(AccountingService.class)
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AccountingServiceImpl implements AccountingService {
    @Inject
    private AccountService accountService;

    @Inject
    private TransactionService transactionService;

    @Inject
    private ExternalPartyService externalPartyService;

    @Inject
    private ReportService reportService;


    @Override
    public List<BaseAccountInfoDTO> getAccounts() {
        return accountService.getAccounts();
    }

    @Override
    public List<AccountDTO> getAccountsTree() {
        return accountService.getAccountsTree();
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        return transactionService.getTransactions();
    }

    @Override
    public List<BaseExternalPartyDTO> getExternalParties() {
        return externalPartyService.getExternalParties();
    }

    @Override
    public DetailedAccountDTO findAccountById(long accountId) {
        return accountService.loadAccountById(accountId);
    }

    @Override
    public TransactionDTO findTransactionById(long transactionId) {
        return transactionService.findTransactionById(transactionId);
    }

    @Override
    public ExternalPartyDTO findExternalPartyById(long organizationId) {
        return externalPartyService.findExternalPartyById(organizationId);
    }

    @Override
    public BaseAccountInfoDTO saveAccount(BaseAccountInfoDTO account) {
        return accountService.saveAccount(account);
    }

    @Override
    public TransactionDTO saveTransaction(TransactionDTO transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @Override
    public BaseExternalPartyDTO saveExternalParty(BaseExternalPartyDTO organization) {
        return externalPartyService.saveExternalParty(organization);
    }

    @Override
    public void deleteAccount(long accountId) {
        accountService.deleteAccount(accountId);
    }

    @Override
    public void deleteTransaction(long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }

    @Override
    public void deleteExternalParty(long organizationId) {
        externalPartyService.deleteExternalParty(organizationId);
    }

    @Override
    public List<BaseAccountInfoDTO> searchAccounts(AccountSearchCriteria criteria) {
        return accountService.searchAccounts(criteria);
    }

    @Override
    public List<TransactionDTO> searchTransactions(TransactionSearchCriteria criteria) {
        return transactionService.searchTransactions(criteria);
    }

    @Override
    public List<BaseExternalPartyDTO> searchExternalParties(ExternalPartySearchCriteria criteria) {
        return externalPartyService.searchExternalParties(criteria);
    }

    @Override
    public BalanceReportResult createBalanceReport(BalanceReportCriteria criteria) {
        return reportService.createBalanceReport(criteria);
    }

    @Override
    public OutcomeReportResult createOutcomeReport(OutcomeReportCriteria criteria) {
        return reportService.createOutcomeReport(criteria);
    }

    @Override
    public IncomeReportResult createIncomeReport(IncomeReportCriteria criteria) {
        return reportService.createIncomeReport(criteria);
    }

    @Override
    public StatusReportResult createStatusReport(StatusReportCriteria criteria) {
        return reportService.createStatusReport(criteria);
    }
}

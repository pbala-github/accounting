package plb.accounting.services.impl;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.*;
import plb.accounting.dto.reporting.*;
import plb.accounting.services.*;

import java.util.List;

/**
 * Entry point class (facade) for service components
 *
 * User: pbala
 * Date: 11/5/12 4:01 PM
 */
public class AccountingService implements IAccountingService {
    /**
     *
     */
    private IAccountService accountService;

    /**
     *
     */
    private ITransactionService transactionService;

    /**
     *
     */
    private IExternalPartyService externalPartyService;

    /**
     *
     */
    private IReportService reportService;


    @Override
    public List<BaseAccountDTO> getAccounts() {
        return accountService.getAccounts();
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
    public AccountDTO findAccountById(long accountId) {
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
    public AccountDTO saveAccount(AccountDTO account) {
        return accountService.saveAccount(account);
    }

    @Override
    public TransactionDTO saveTransaction(TransactionDTO transaction) {
        return transactionService.saveTransaction(transaction);
    }

    @Override
    public ExternalPartyDTO saveExternalParty(ExternalPartyDTO organization) {
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
    public List<BaseAccountDTO> searchAccounts(AccountSearchCriteria criteria) {
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
}

package plb.accounting.services.impl;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.*;
import plb.accounting.dto.reporting.*;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;
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
    private IExternalOrganizationService externalOrganizationService;

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
    public List<BaseExternalOrganizationDTO> getExternalOrganizations() {
        return externalOrganizationService.getExternalOrganizations();
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
    public ExternalOrganizationDTO findExternalOrganizationById(long organizationId) {
        return externalOrganizationService.findExternalOrganizationById(organizationId);
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
    public ExternalOrganizationDTO saveExternalOrganization(ExternalOrganizationDTO organization) {
        return externalOrganizationService.saveExternalOrganization(organization);
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
    public void deleteExternalOrganization(long organizationId) {
        externalOrganizationService.deleteExternalOrganization(organizationId);
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
    public List<BaseExternalOrganizationDTO> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria) {
        return externalOrganizationService.searchExternalOrganizations(criteria);
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

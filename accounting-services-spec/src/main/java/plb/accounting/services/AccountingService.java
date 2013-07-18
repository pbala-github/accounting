package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.*;
import plb.accounting.dto.reporting.*;

import java.util.List;

/**
 * This is the entry point interface for the service components
 *
 * User: pbala
 * Date: 10/30/12 9:38 AM
 */
public interface AccountingService {

    List<AccountDTO> getAccountsTree();

    DetailedAccountDTO findAccountById(long accountId);

    TransactionDTO findTransactionById(long transactionId);

    ExternalPartyDTO findExternalPartyById(long organizationId);


    BaseAccountInfoDTO saveAccount(BaseAccountInfoDTO account);

    TransactionDTO saveTransaction(TransactionDTO transaction);

    BaseExternalPartyDTO saveExternalParty(BaseExternalPartyDTO organization);


    void deleteAccount(long accountId);

    void deleteTransaction(long transactionId);

    void deleteExternalParty(long organizationId);


    List<BaseAccountInfoDTO> searchAccounts(AccountSearchCriteria criteria);

    List<TransactionDTO> searchTransactions(TransactionSearchCriteria criteria);

    List<BaseExternalPartyDTO> searchExternalParties(ExternalPartySearchCriteria criteria);

    BalanceReportResult createBalanceReport(BalanceReportCriteria criteria);

    OutcomeReportResult createOutcomeReport(OutcomeReportCriteria criteria);

    IncomeReportResult createIncomeReport(IncomeReportCriteria criteria);

    StatusReportResult createStatusReport(StatusReportCriteria criteria);

}

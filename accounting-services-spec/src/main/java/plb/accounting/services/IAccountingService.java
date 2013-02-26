package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.*;

import java.util.List;

/**
 * This is the entry point interface for the service components
 *
 * User: pbala
 * Date: 10/30/12 9:38 AM
 */
public interface IAccountingService extends IReportService{

    List<BaseAccountDTO> getAccounts();

    List<TransactionDTO> getTransactions();

    List<BaseExternalPartyDTO> getExternalParties();


    DetailedAccountDTO findAccountById(long accountId);

    TransactionDTO findTransactionById(long transactionId);

    ExternalPartyDTO findExternalPartyById(long organizationId);


    BaseAccountDTO saveAccount(BaseAccountDTO account);

    TransactionDTO saveTransaction(TransactionDTO transaction);

    ExternalPartyDTO saveExternalParty(ExternalPartyDTO organization);


    void deleteAccount(long accountId);

    void deleteTransaction(long transactionId);

    void deleteExternalParty(long organizationId);


    List<BaseAccountDTO> searchAccounts(AccountSearchCriteria criteria);

    List<TransactionDTO> searchTransactions(TransactionSearchCriteria criteria);

    List<BaseExternalPartyDTO> searchExternalParties(ExternalPartySearchCriteria criteria);

}

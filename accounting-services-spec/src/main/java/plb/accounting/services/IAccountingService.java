package plb.accounting.services;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
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

    List<AccountDTO> getAccounts();

    List<TransactionDTO> getTransactions();

    List<BaseExternalOrganizationDTO> getExternalOrganizations();


    DetailedAccountDTO findAccountById(long accountId);

    TransactionDTO findTransactionById(long transactionId);

    ExternalOrganizationDTO findExternalOrganizationById(long organizationId);


    DetailedAccountDTO saveAccount(DetailedAccountDTO account);

    TransactionDTO saveTransaction(TransactionDTO transaction);

    ExternalOrganizationDTO saveExternalOrganization(ExternalOrganizationDTO organization);


    void deleteAccount(long accountId);

    void deleteTransaction(long transactionId);

    void deleteExternalOrganization(long organizationId);


    List<AccountDTO> searchAccounts(AccountSearchCriteria criteria);

    List<TransactionDTO> searchTransactions(TransactionSearchCriteria criteria);

    List<BaseExternalOrganizationDTO> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria);

}

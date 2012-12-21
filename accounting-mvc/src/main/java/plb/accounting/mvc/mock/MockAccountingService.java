package plb.accounting.mvc.mock;

import org.springframework.stereotype.Service;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.*;
import plb.accounting.dto.reporting.*;
import plb.accounting.services.IAccountingService;
import plb.accounting.common.test.helpers.*;

import java.util.Arrays;
import java.util.List;

/**
 * User: pbala
 * Date: 12/18/12 10:27 PM
 */
@Service
public class MockAccountingService implements IAccountingService{
    @Override
    public List<BaseAccountDTO> getAccounts() {
        return Arrays.asList(new BaseAccountDTO());  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<BaseExternalOrganizationDTO> getExternalOrganizations() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccountDTO findAccountById(long accountId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TransactionDTO findTransactionById(long transactionId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ExternalOrganizationDTO findExternalOrganizationById(long organizationId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public AccountDTO saveAccount(AccountDTO account) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public TransactionDTO saveTransaction(TransactionDTO transaction) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public ExternalOrganizationDTO saveExternalOrganization(ExternalOrganizationDTO organization) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteAccount(long accountId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteTransaction(long transactionId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteExternalOrganization(long organizationId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<BaseAccountDTO> searchAccounts(AccountSearchCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<TransactionDTO> searchTransactions(TransactionSearchCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<BaseExternalOrganizationDTO> searchExternalOrganizations(ExternalOrganizationSearchCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public BalanceReportResult createBalanceReport(BalanceReportCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public OutcomeReportResult createOutcomeReport(OutcomeReportCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IncomeReportResult createIncomeReport(IncomeReportCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

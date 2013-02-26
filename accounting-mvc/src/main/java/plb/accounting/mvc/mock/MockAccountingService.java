package plb.accounting.mvc.mock;

import org.springframework.stereotype.Service;
import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.*;
import plb.accounting.dto.reporting.*;
import plb.accounting.services.IAccountingService;

import java.math.BigDecimal;
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

        return createAccounts();
    }

    private List<BaseAccountDTO> createAccounts(){
        BaseAccountDTO baseAccountDTO = new BaseAccountDTO();
        baseAccountDTO.setCurrentBalance(BigDecimal.ONE);
        baseAccountDTO.setDescription("description 1");
        baseAccountDTO.setId(1l);
        baseAccountDTO.setInitialBalance(BigDecimal.TEN);
        baseAccountDTO.setName("name 1");
        baseAccountDTO.setType(AccountTypeEnum.INCOME);

        BaseAccountDTO baseAccountDTO2 = new BaseAccountDTO();
        baseAccountDTO2.setCurrentBalance(BigDecimal.ONE);
        baseAccountDTO2.setDescription("description 2");
        baseAccountDTO2.setId(2l);
        baseAccountDTO2.setInitialBalance(BigDecimal.TEN);
        baseAccountDTO2.setName("name 2");
        baseAccountDTO2.setType(AccountTypeEnum.INCOME);

        BaseAccountDTO baseAccountDTO3 = new BaseAccountDTO();
        baseAccountDTO3.setCurrentBalance(BigDecimal.ONE);
        baseAccountDTO3.setDescription("description 3");
        baseAccountDTO3.setId(3l);
        baseAccountDTO3.setInitialBalance(BigDecimal.TEN);
        baseAccountDTO3.setName("name 3");
        baseAccountDTO3.setType(AccountTypeEnum.INCOME);

        baseAccountDTO2.setChildrenAccounts(Arrays.asList(baseAccountDTO3));
        baseAccountDTO.setChildrenAccounts(Arrays.asList(baseAccountDTO2));

        return Arrays.asList(baseAccountDTO);
    }

    @Override
    public List<TransactionDTO> getTransactions() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<BaseExternalPartyDTO> getExternalParties() {
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
    public ExternalPartyDTO findExternalPartyById(long organizationId) {
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
    public ExternalPartyDTO saveExternalParty(ExternalPartyDTO organization) {
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
    public void deleteExternalParty(long organizationId) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<BaseAccountDTO> searchAccounts(AccountSearchCriteria criteria) {
        return createAccounts();
    }

    @Override
    public List<TransactionDTO> searchTransactions(TransactionSearchCriteria criteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<BaseExternalPartyDTO> searchExternalParties(ExternalPartySearchCriteria criteria) {
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

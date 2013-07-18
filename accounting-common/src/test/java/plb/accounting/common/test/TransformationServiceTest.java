package plb.accounting.common.test;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.Mapper;
import org.junit.BeforeClass;
import org.junit.Test;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.common.transformation.DozerTransformationService;
import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.reporting.IGroupingReportCriteria;
import plb.accounting.dto.reporting.OutcomeReportCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.AccountComposite;
import plb.accounting.model.AccountTypeEnum;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;

/**
 * User: pbala
 * Date: 11/6/12 11:09 AM
 */
public class TransformationServiceTest {

    private static ITransformationService transformationService;

    @BeforeClass
    public static void setup() {
        transformationService = new DozerTransformationService();
        Mapper dozerBeanMapper = DozerBeanMapperSingletonWrapper.getInstance();
        ((DozerBeanMapper) dozerBeanMapper).setMappingFiles(Arrays.asList("file:/home/pbala/myProjects/accounting/accounting-common/src/main/resources/config/dozer/dozerBeanMapping.xml"));
        ((DozerTransformationService) transformationService).setMapper(dozerBeanMapper);


    }

    @Test
    public void transformAccount() {


        Account account = new Account("Account name", AccountTypeEnum.INCOME, new BigDecimal(43));
        account.setDescription("Desctiption");

        AccountComposite parent = new AccountComposite("Parent Account name", AccountTypeEnum.INCOME);
        parent.setDescription("Desctiption44");
        account.setParentAccount(parent);


        AccountDTO baseAccountDTO = transformationService.transform(account, AccountDTO.class);

        assertEquals(account.getCurrentBalance(), baseAccountDTO.getCurrentBalance());
        assertEquals(account.getDescription(), baseAccountDTO.getDescription());
        assertEquals(account.getInitialBalance(), baseAccountDTO.getInitialBalance());
        assertEquals(account.getName(), baseAccountDTO.getName());
        assertEquals(account.getType().name(), baseAccountDTO.getType().name());
        assertEquals(account.getId(), baseAccountDTO.getId());


//        assertEquals(account.getParentAccount().getCurrentBalance(),baseAccountDTO.getParentAccount().getCurrentBalance());
        assertEquals(account.getParentAccount().getDescription(), baseAccountDTO.getParentAccount().getDescription());
        assertEquals(account.getParentAccount().getInitialBalance(), baseAccountDTO.getParentAccount().getInitialBalance());
        assertEquals(account.getParentAccount().getName(), baseAccountDTO.getParentAccount().getName());
        assertEquals(account.getParentAccount().getType().name(), baseAccountDTO.getParentAccount().getType().name());
        assertEquals(account.getParentAccount().getId(), baseAccountDTO.getParentAccount().getId());
    }

    @Test
    public void transformGroupingReportCriteriaToTransactionSearchCriteria() {
        OutcomeReportCriteria outcomeReportCriteria = new OutcomeReportCriteria();
        Calendar calendar = Calendar.getInstance();
        outcomeReportCriteria.setEndDate(calendar.getTime());
        calendar.add(Calendar.MONTH, -1);
        outcomeReportCriteria.setStartDate(calendar.getTime());
        outcomeReportCriteria.setGroupBy(IGroupingReportCriteria.GroupType.ACCOUNT);
        outcomeReportCriteria.getIncludedAccountsIds().add(111l);

        TransactionSearchCriteria transactionSearchCriteria = transformationService.transform(outcomeReportCriteria, TransactionSearchCriteria.class);

        assertEquals(outcomeReportCriteria.getIncludedAccountsIds(),transactionSearchCriteria.getDestinationAccountIds());
        assertEquals(outcomeReportCriteria.getIncludedAccountsIds(),transactionSearchCriteria.getOriginAccountIds());
        assertEquals(outcomeReportCriteria.getEndDate(), transactionSearchCriteria.getExecutionDateTo());
        assertEquals(outcomeReportCriteria.getStartDate(),transactionSearchCriteria.getExecutionDateFrom());
    }
}

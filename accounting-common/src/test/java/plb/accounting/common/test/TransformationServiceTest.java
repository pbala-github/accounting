package plb.accounting.common.test;

import org.dozer.DozerBeanMapper;
import org.dozer.DozerBeanMapperSingletonWrapper;
import static org.junit.Assert.*;

import org.dozer.Mapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import plb.accounting.common.transformation.DozerTransformationService;
import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * User: pbala
 * Date: 11/6/12 11:09 AM
 */
public class TransformationServiceTest {

    private static ITransformationService transformationService;

    @BeforeClass
    public static void setup(){
        transformationService = new DozerTransformationService();
        Mapper dozerBeanMapper = DozerBeanMapperSingletonWrapper.getInstance();
        ((DozerBeanMapper)dozerBeanMapper).setMappingFiles(Arrays.asList("file:/home/pbala/myProjects/accounting/accounting-common/src/main/resources/config/dozer/dozerBeanMapping.xml"));
        ((DozerTransformationService)transformationService).setMapper(dozerBeanMapper);


    }

    @Test
    public void transformAccount(){


        Account account = BeanUtils.instantiateClass(Account.class);
        account.setCurrentBalance(new BigDecimal(33));
        account.setDescription("Desctiption");
        account.setInitialBalance(new BigDecimal(43));
        account.setName("Account name");
        account.setType(AccountTypeEnum.INCOME);

        Account parent = new Account();
        parent.setCurrentBalance(new BigDecimal(343));
        parent.setDescription("Desctiption44");
        parent.setInitialBalance(new BigDecimal(443));
        parent.setName("Parent Account name");
        parent.setType(AccountTypeEnum.STORAGE);

        account.setParentAccount(parent);



        BaseAccountDTO baseAccountDTO = transformationService.transform(account,BaseAccountDTO.class);

        assertEquals(account.getCurrentBalance(),baseAccountDTO.getCurrentBalance());
        assertEquals(account.getDescription(),baseAccountDTO.getDescription());
        assertEquals(account.getInitialBalance(),baseAccountDTO.getInitialBalance());
        assertEquals(account.getName(),baseAccountDTO.getName());
        assertEquals(account.getType().name(),baseAccountDTO.getType().name());
        assertEquals(account.getId(),baseAccountDTO.getId());


        assertEquals(account.getParentAccount().getCurrentBalance(),baseAccountDTO.getParentAccount().getCurrentBalance());
        assertEquals(account.getParentAccount().getDescription(),baseAccountDTO.getParentAccount().getDescription());
        assertEquals(account.getParentAccount().getInitialBalance(),baseAccountDTO.getParentAccount().getInitialBalance());
        assertEquals(account.getParentAccount().getName(),baseAccountDTO.getParentAccount().getName());
        assertEquals(account.getParentAccount().getType().name(),baseAccountDTO.getParentAccount().getType().name());
        assertEquals(account.getParentAccount().getId(),baseAccountDTO.getParentAccount().getId());
    }
}

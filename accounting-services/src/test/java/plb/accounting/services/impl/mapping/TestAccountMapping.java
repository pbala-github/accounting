package plb.accounting.services.impl.mapping;

import org.junit.Test;
import org.junit.runner.RunWith;
import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.AccountDTO;
import plb.accounting.model.Account;
import plb.accounting.model.AccountComposite;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.services.test.WeldJUnit4Runner;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * User: pbala
 * Date: 11/6/12 11:09 AM
 */
@RunWith(WeldJUnit4Runner.class)
public class TestAccountMapping {

    @Inject
    private ITransformationService transformationService;

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
}

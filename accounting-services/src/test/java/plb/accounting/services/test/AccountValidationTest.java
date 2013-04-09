package plb.accounting.services.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import plb.accounting.common.validation.ValidationErrorList;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.AccountTypeEnum;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.services.impl.validation.AccountCreation;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 * User: pbala
 * Date: 1/14/13 3:48 PM
 */
@RunWith(WeldJUnit4Runner.class)
public class AccountValidationTest extends BaseValidationTest {

    @Test
    public void accountCreationValidations() {
        BaseAccountDTO account = new BaseAccountDTO();
        ValidationErrorList errorList = getValidator().validate(account, AccountCreation.class);

        printValidationErrors(errorList);
        assertEquals(errorList.getErrors().size(), 3);

        account.setName("n");
        account.setDescription("d");
        account.setInitialBalance(new BigDecimal(-1));

        errorList = getValidator().validate(account, AccountCreation.class);

        printValidationErrors(errorList);
        assertEquals(errorList.getErrors().size(), 2);

        account.setName("na");
        account.setInitialBalance(new BigDecimal(34));

        errorList = getValidator().validate(account, AccountCreation.class);

        printValidationErrors(errorList);
        assertEquals(errorList.getErrors().size(), 1);

        account.setType(AccountTypeEnum.INCOME);
        errorList = getValidator().validate(account, AccountCreation.class);

        printValidationErrors(errorList);
        assertEquals(errorList.getErrors().size(), 0);

        account.setType(null);
        BaseAccountDTO parentAccount = new BaseAccountDTO();
        parentAccount.setId(new Long(1));
//        parentAccount.setType(AccountTypeEnum.INCOME);
//        parentAccount.setDescription("description");
//        parentAccount.setName("name");

        account.setParentAccount(parentAccount);
        errorList = getValidator().validate(account, AccountCreation.class);
        printValidationErrors(errorList);
        assertEquals(errorList.getErrors().size(), 0);

    }

    //    @Test
    public void accountValidations() {
        AccountDTO account = new AccountDTO();
        ValidationErrorList errorList = getValidator().validate(account);

        printValidationErrors(errorList);
        assertEquals(errorList.getErrors().size(), 3);

        account.setType(AccountTypeEnum.OUTCOME);
        account.setName("name");
        account.setDescription("description");
        account.setCurrentBalance(new BigDecimal(-23));

        errorList = getValidator().validate(account);

        printValidationErrors(errorList);
        assertEquals(errorList.getErrors().size(), 1);

        account.setCurrentBalance(new BigDecimal(23));

        errorList = getValidator().validate(account);

        printValidationErrors(errorList);
        assertEquals(errorList.getErrors().size(), 0);

    }
}

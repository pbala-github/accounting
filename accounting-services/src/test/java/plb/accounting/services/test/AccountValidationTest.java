package plb.accounting.services.test;

import org.junit.Test;
import org.springframework.util.Assert;
import plb.accounting.common.validation.ValidationErrorList;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.AccountTypeEnum;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.validation.AccountCreation;

import javax.validation.groups.Default;
import java.math.BigDecimal;

/**
 * User: pbala
 * Date: 1/14/13 3:48 PM
 */
public class AccountValidationTest extends BaseValidationTest{

    @Test
    public void accountCreationValidations(){
        BaseAccountDTO account = new BaseAccountDTO();
        ValidationErrorList errorList = getValidator().validate(account, AccountCreation.class);

        printValidationErrors(errorList);
        Assert.isTrue(errorList.getErrors().size() == 3);

        account.setName("n");
        account.setDescription("d");
        account.setInitialBalance(new BigDecimal(-1));

        errorList = getValidator().validate(account, AccountCreation.class);

        printValidationErrors(errorList);
        Assert.isTrue(errorList.getErrors().size() == 3);

        account.setName("na");
        account.setInitialBalance(new BigDecimal(34));

        errorList = getValidator().validate(account, AccountCreation.class);

        printValidationErrors(errorList);
        Assert.isTrue(errorList.getErrors().size() == 1);

        account.setType(AccountTypeEnum.INCOME);
        errorList = getValidator().validate(account, AccountCreation.class);

        printValidationErrors(errorList);
        Assert.isTrue(errorList.getErrors().size() == 0);
        
        account.setType(null);
        BaseAccountDTO parentAccount = new BaseAccountDTO();
        parentAccount.setId(new Long(1));
//        parentAccount.setType(AccountTypeEnum.INCOME);
//        parentAccount.setDescription("description");
//        parentAccount.setName("name");

        account.setParentAccount(parentAccount);
        errorList = getValidator().validate(account, AccountCreation.class);
        printValidationErrors(errorList);
        Assert.isTrue(errorList.getErrors().size() == 0);

    }

//    @Test
    public void accountValidations(){
        AccountDTO account = new AccountDTO();
        ValidationErrorList errorList = getValidator().validate(account);

        printValidationErrors(errorList);
        Assert.isTrue(errorList.getErrors().size() == 3);

        account.setType(AccountTypeEnum.OUTCOME);
        account.setName("name");
        account.setDescription("description");
        account.setCurrentBalance(new BigDecimal(-23));

        errorList = getValidator().validate(account);

        printValidationErrors(errorList);
        Assert.isTrue(errorList.getErrors().size() == 1);

        account.setCurrentBalance(new BigDecimal(23));

        errorList = getValidator().validate(account);

        printValidationErrors(errorList);
        Assert.isTrue(errorList.getErrors().size() == 0);

    }
}

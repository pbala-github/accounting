package plb.accounting.services.test;

import junit.framework.Assert;
import org.junit.Test;
import plb.accounting.common.validation.ValidationErrorList;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.AccountTypeEnum;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.dto.validation.TransactionCreation;

import java.math.BigDecimal;
import java.util.Date;

/**
 * User: pbala
 * Date: 1/14/13 4:26 PM
 */
public class TransactionValidationTest extends BaseValidationTest{

    @Test
    public void transactionCreationValidations(){
        TransactionDTO transaction = new TransactionDTO();
        ValidationErrorList errorList = getValidator().validate(transaction);

        printValidationErrors(errorList);
        Assert.assertEquals(4,errorList.getErrors().size());

        transaction.setExecutionDate(new Date());
        transaction.setAmount(BigDecimal.TEN);
        transaction.setDescription("Transaction description");

        errorList = getValidator().validate(transaction);

        printValidationErrors(errorList);
        Assert.assertEquals(2,errorList.getErrors().size());

        AccountDTO dstAccount = new AccountDTO();
        transaction.setDestinationAccount(dstAccount);
        dstAccount.setCurrentBalance(BigDecimal.ONE);
        dstAccount.setDescription("Description");
        dstAccount.setName("name");
        dstAccount.setType(AccountTypeEnum.OUTCOME);

        AccountDTO srcAccount = new AccountDTO();
        transaction.setOriginAccount(srcAccount);
        srcAccount.setCurrentBalance(BigDecimal.ONE);
        srcAccount.setDescription("Description");
        srcAccount.setName("name");
        srcAccount.setType(AccountTypeEnum.OUTCOME);

        errorList = getValidator().validate(transaction);

        printValidationErrors(errorList);
        Assert.assertEquals(1,errorList.getErrors().size());

        srcAccount.setCurrentBalance(new BigDecimal(34));

        errorList = getValidator().validate(transaction);

        printValidationErrors(errorList);
        Assert.assertEquals(0,errorList.getErrors().size());
    }
}

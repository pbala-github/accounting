package plb.accounting.services.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import plb.accounting.common.validation.ValidationErrorList;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.TransactionDTO;

import java.math.BigDecimal;
import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * User: pbala
 * Date: 1/14/13 4:26 PM
 */
@RunWith(WeldJUnit4Runner.class)
public class TransactionValidationTest extends BaseValidationTest {

    @Test
    public void transactionCreationValidations() {
        TransactionDTO transaction = new TransactionDTO();
        ValidationErrorList errorList = getValidator().validate(transaction);

        printValidationErrors(errorList);
        assertEquals(4, errorList.getErrors().size());

        transaction.setExecutionDate(new Date());
        transaction.setAmount(BigDecimal.TEN);
        transaction.setDescription("Transaction description");

        errorList = getValidator().validate(transaction);

        printValidationErrors(errorList);
        assertEquals(2, errorList.getErrors().size());

        AccountDTO dstAccount = new AccountDTO();
        dstAccount.setId(new Long(1));
        transaction.setDestinationAccount(dstAccount);
//        dstAccount.setCurrentBalance(BigDecimal.ONE);
//        dstAccount.setDescription("Description");
//        dstAccount.setName("name");
//        dstAccount.setType(AccountTypeEnum.OUTCOME);

        AccountDTO srcAccount = new AccountDTO();
        srcAccount.setId(new Long(2));
        transaction.setOriginAccount(srcAccount);
//        srcAccount.setCurrentBalance(BigDecimal.ONE);
//        srcAccount.setDescription("Description");
//        srcAccount.setName("name");
//        srcAccount.setType(AccountTypeEnum.OUTCOME);

        errorList = getValidator().validate(transaction);

        printValidationErrors(errorList);
        assertEquals(1, errorList.getErrors().size());

        srcAccount.setCurrentBalance(new BigDecimal(34));

        errorList = getValidator().validate(transaction);

        printValidationErrors(errorList);
        assertEquals(0, errorList.getErrors().size());
    }
}

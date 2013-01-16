package plb.accounting.services.test;

import org.junit.Assert;
import org.junit.Test;
import plb.accounting.common.validation.ValidationErrorList;
import plb.accounting.dto.reporting.BalanceReportCriteria;
import plb.accounting.dto.reporting.IGroupingReportCriteria;

import java.util.Date;

/**
 * User: pbala
 * Date: 1/15/13 9:55 AM
 */
public class ReportsValidationTest extends BaseValidationTest{

    @Test
    public void validateGroupingReportCriteria(){
        BalanceReportCriteria criteria = new BalanceReportCriteria();
        criteria.setGroupBy(null);
        ValidationErrorList errors = getValidator().validate(criteria);

        //all fields are invalid
        printValidationErrors(errors);
        Assert.assertEquals(4,errors.getErrors().size());

        criteria.setGroupBy(IGroupingReportCriteria.GroupType.ACCOUNT);
        criteria.setStartDate(new Date());
        criteria.setEndDate(new Date());

        errors = getValidator().validate(criteria);

        //the field includedAccounts is invalid (empty)
        printValidationErrors(errors);
        Assert.assertEquals(1,errors.getErrors().size());

        criteria.addIncludedAccount(null);

        errors = getValidator().validate(criteria);

        //the field includedAccounts is invalid (only null values)
        printValidationErrors(errors);
        Assert.assertEquals(1,errors.getErrors().size());

        criteria.addIncludedAccount(1l);

        errors = getValidator().validate(criteria);

        //the field includedAccounts is invalid (includes null value)
        printValidationErrors(errors);
        Assert.assertEquals(1,errors.getErrors().size());

        criteria.getIncludedAccountsIds().clear();
        criteria.addIncludedAccount(1l);

        errors = getValidator().validate(criteria);

        //all fields valid
        printValidationErrors(errors);
        Assert.assertEquals(0,errors.getErrors().size());

    }
}

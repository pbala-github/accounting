package plb.accounting.services.impl.reporting;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import plb.accounting.model.view.TransactionView;

import java.util.List;

/**
 * @author: pbala
 */
public class TestGroupStrategy {


    private static List<TransactionView> transactionViews;

    @BeforeClass
    public static void globalSetUp() {
        transactionViews = SampleReportData.getSampleTransactionViews();
    }


    @Test
    public void testGroupTransactionsByPeriod() {
        TransactionPeriodGroupStrategy groupStrategy = new TransactionPeriodGroupStrategy();
        GroupContainer<Period, TransactionView> group = groupStrategy.group(null, transactionViews);
        Assert.assertEquals(3, group.getGroupList().size());
    }

    @Test
    public void testGroupTransactionsByAccount() {
        TransactionAccountGroupStrategy groupStrategy = new TransactionAccountGroupStrategy();
        GroupContainer<AccountGroupKey, TransactionView> group = groupStrategy.group(null, transactionViews);
        Assert.assertEquals(7, group.getGroupList().size());
    }
}

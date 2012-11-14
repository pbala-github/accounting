package plb.accounting.services.impl.reporting;

import org.apache.commons.lang.time.DateUtils;
import plb.accounting.dto.reporting.IGroupingReportCriteria;
import plb.accounting.model.Transaction;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 12:17 PM
 */
public class TransactionPeriodGroupStrategy implements IGroupStrategy<IPeriod,Transaction> {

    @Override
    public GroupContainer<IPeriod, Transaction> group(IGroupingReportCriteria criteria, List<Transaction> data) {

        GroupContainer<IPeriod,Transaction> groupContainer = new GroupContainer<IPeriod, Transaction>();
        //group data
        for(Transaction t : data){

            IPeriod period = getPeriod(t.getExecutionDate());
            Group<IPeriod, Transaction> group = groupContainer.getGroupWithKey(period, true);

            group.addItem(t);

        }

        return groupContainer;
    }

    private IPeriod getPeriod(Date date) {

        Date startPoint = DateUtils.truncate(date,Calendar.DAY_OF_MONTH);
        Date endPoint = DateUtils.addDays(DateUtils.addMonths(startPoint, 1), -1);

        return new Period(startPoint,endPoint);
    }

    private String getPeriodIdentifier(Date executionDate) {
        return executionDate.getMonth() + "_" + executionDate.getYear();
    }

}

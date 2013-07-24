package plb.accounting.services.impl.reporting;

import org.apache.commons.lang.time.DateUtils;
import plb.accounting.dto.reporting.IGroupingReportCriteria;
import plb.accounting.model.Transaction;
import plb.accounting.model.view.TransactionView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 12:17 PM
 */
public class TransactionPeriodGroupStrategy implements IGroupStrategy<Period,TransactionView> {

    @Override
    public GroupContainer<Period, TransactionView> group(IGroupingReportCriteria criteria, List<TransactionView> data) {

        GroupContainer<Period,TransactionView> groupContainer = new GroupContainer<Period, TransactionView>();
        //group data
        for(TransactionView t : data){

            Period period = getPeriod(t.getExecutionDate());
            Group<Period, TransactionView> group = groupContainer.getGroupWithKey(period, true);

            group.addItem(t);

        }

        return groupContainer;
    }

    private Period getPeriod(Date date) {

        Date startPoint = DateUtils.truncate(date,Calendar.MONTH);
        Date endPoint = DateUtils.addDays(DateUtils.addMonths(startPoint, 1), -1);

        return new Period(startPoint,endPoint);
    }

    private String getPeriodIdentifier(Date executionDate) {
        return executionDate.getMonth() + "_" + executionDate.getYear();
    }

}

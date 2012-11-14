package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.IGroupingReportCriteria;
import plb.accounting.model.Account;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 12:44 PM
 */
public class TransactionAccountGroupStrategy implements IGroupStrategy<Account,Transaction>{

    @Override
    public GroupContainer<Account, Transaction> group(IGroupingReportCriteria criteria, List<Transaction> data) {

        GroupContainer<Account,Transaction> groupContainer = new GroupContainer<Account, Transaction>();
        for(Transaction t : data){

            Group<Account,Transaction> group = groupContainer.getGroupWithKey(t.getOriginAccount(),true);
            group.addItem(t);

            group = groupContainer.getGroupWithKey(t.getDestinationAccount(),true);
            group.addItem(t);

        }

        return groupContainer;
    }

}

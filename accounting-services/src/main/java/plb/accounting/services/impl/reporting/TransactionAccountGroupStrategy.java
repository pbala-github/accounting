package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.IGroupingReportCriteria;
import plb.accounting.model.view.TransactionView;

import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 12:44 PM
 */
public class TransactionAccountGroupStrategy implements IGroupStrategy<AccountGroupKey, TransactionView> {

    @Override
    public GroupContainer<AccountGroupKey, TransactionView> group(IGroupingReportCriteria criteria, List<TransactionView> data) {

        GroupContainer<AccountGroupKey, TransactionView> groupContainer = new GroupContainer<AccountGroupKey, TransactionView>();
        for (TransactionView t : data) {

            Group<AccountGroupKey, TransactionView> group = groupContainer.getGroupWithKey(AccountGroupKey.fromTransactionViewAsOrigin(t), true);
            group.addItem(t);

            group = groupContainer.getGroupWithKey(AccountGroupKey.fromTransactionViewAsDestination(t), true);
            group.addItem(t);

        }

        return groupContainer;
    }

}

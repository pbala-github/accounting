package plb.accounting.services.impl.reporting;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.reporting.*;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.Transaction;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 1:07 PM
 */
public class AccountOutcomeReportStrategy implements IReportStrategy<OutcomeReportResult,OutcomeReportCriteria>{

    @Inject
    protected ITransformationService transformationService;

    @Inject
    private IGroupStrategy<Account,Transaction> groupStrategy ;//= new TransactionAccountGroupStrategy();

    @Override
    public OutcomeReportResult createReport(OutcomeReportCriteria reportCriteria, Object data) {

        OutcomeReportResult result = new OutcomeReportResult(reportCriteria);
        List<Transaction> transactions = (List<Transaction>) data;

        GroupContainer<Account,Transaction> groupContainer = groupStrategy.group(reportCriteria,transactions);

        //aggregate total amounts
        BigDecimal totalOutcome =BigDecimal.ZERO;
        for(Group<Account, Transaction> group : groupContainer.getGroupList()){
            BigDecimal outcome = BigDecimal.ZERO;

            for(Transaction t : group.getItems()){
                if(t.getDestinationAccount().getType().equals(AccountTypeEnum.OUTCOME))
                    outcome = outcome.add(t.getAmount());
            }

            totalOutcome = totalOutcome.add(outcome);
            result.addResultEntry(0,outcome.doubleValue(),transformationService.transform(group.getKey(), AccountDTO.class));
        }

        result.setTotalOutcome(totalOutcome.doubleValue());
        return result;

    }


    @Override
    public boolean supports(ReportCriteria reportCriteria) {
        if (OutcomeReportCriteria.class.isAssignableFrom(reportCriteria.getClass()) &&
                IGroupingReportCriteria.GroupType.ACCOUNT.equals(((OutcomeReportCriteria) reportCriteria).getGroupBy()))
            return true;

        return false;
    }

}

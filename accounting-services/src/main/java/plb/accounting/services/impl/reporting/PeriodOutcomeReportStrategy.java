package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.OutcomeReportCriteria;
import plb.accounting.dto.reporting.OutcomeReportResult;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 12:58 PM
 */
public class PeriodOutcomeReportStrategy implements IReportStrategy<OutcomeReportResult,OutcomeReportCriteria>{

    @Override
    public OutcomeReportResult createReport(OutcomeReportCriteria reportCriteria, Object data) {
        OutcomeReportResult result = new OutcomeReportResult();
        List<Transaction> transactions = (List<Transaction>) data;

        IGroupStrategy<IPeriod,Transaction> groupStrategy = new TransactionPeriodGroupStrategy();

        GroupContainer<IPeriod,Transaction> groupContainer = groupStrategy.group(reportCriteria,transactions);

        //aggregate total amounts
        BigDecimal totalOutcome =BigDecimal.ZERO;
        for(Group<IPeriod, Transaction> group : groupContainer.getGroupList()){
            BigDecimal outcome = BigDecimal.ZERO;

            for(Transaction t : group.getItems()){
                if(t.getDestinationAccount().getType().equals(AccountTypeEnum.OUTCOME))
                    outcome = outcome.add(t.getAmount());

            }

            totalOutcome = totalOutcome.add(outcome);
            result.addResultEntry(0,outcome.doubleValue(),group.getKey().getStartPoint(),group.getKey().getEndPoint());
        }

        result.setTotalOutcome(totalOutcome.doubleValue());
        return result;
    }

}

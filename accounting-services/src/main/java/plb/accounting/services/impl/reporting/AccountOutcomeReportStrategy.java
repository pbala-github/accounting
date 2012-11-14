package plb.accounting.services.impl.reporting;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.reporting.OutcomeReportCriteria;
import plb.accounting.dto.reporting.OutcomeReportResult;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 1:07 PM
 */
public class AccountOutcomeReportStrategy implements IReportStrategy<OutcomeReportResult,OutcomeReportCriteria>{

    protected ITransformationService transformationService;

    @Override
    public OutcomeReportResult createReport(OutcomeReportCriteria reportCriteria, Object data) {

        OutcomeReportResult result = new OutcomeReportResult();
        List<Transaction> transactions = (List<Transaction>) data;

        IGroupStrategy<Account,Transaction> groupStrategy = new TransactionAccountGroupStrategy();

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
            result.addResultEntry(0,outcome.doubleValue(),transformationService.transform(group.getKey(), BaseAccountDTO.class));
        }

        result.setTotalOutcome(totalOutcome.doubleValue());
        return result;

    }

}

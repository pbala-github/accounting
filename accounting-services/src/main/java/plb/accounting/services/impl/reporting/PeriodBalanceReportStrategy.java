package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.BalanceReportCriteria;
import plb.accounting.dto.reporting.BalanceReportResult;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.Transaction;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/10/12 11:16 PM
 */
public class PeriodBalanceReportStrategy implements IReportStrategy<BalanceReportResult,BalanceReportCriteria>{

    @Inject
    private  IGroupStrategy<IPeriod,Transaction> groupStrategy;// = new TransactionPeriodGroupStrategy();

    @Override
    public BalanceReportResult createReport(BalanceReportCriteria reportCriteria, Object data) {

        BalanceReportResult result = new BalanceReportResult();
        List<Transaction> transactions = (List<Transaction>) data;

         GroupContainer<IPeriod,Transaction> groupContainer = groupStrategy.group(reportCriteria,transactions);

        //aggregate total amounts
        for(Group<IPeriod, Transaction> group : groupContainer.getGroupList()){
            BigDecimal income =BigDecimal.ZERO, outcome = BigDecimal.ZERO;

            for(Transaction t : group.getItems()){
                if(t.getDestinationAccount().getType().equals(AccountTypeEnum.OUTCOME))
                    outcome = outcome.add(t.getAmount());
                else
                    income = income.add(t.getAmount());
            }

            result.addResultEntry(income.doubleValue(),outcome.doubleValue(),group.getKey().getStartPoint(),group.getKey().getEndPoint());
        }

        return result;  
    }


}

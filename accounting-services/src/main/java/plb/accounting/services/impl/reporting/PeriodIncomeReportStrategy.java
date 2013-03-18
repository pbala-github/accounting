package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.IncomeReportCriteria;
import plb.accounting.dto.reporting.IncomeReportResult;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.Transaction;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 12:58 PM
 */
public class PeriodIncomeReportStrategy implements IReportStrategy<IncomeReportResult,IncomeReportCriteria>{

    @Inject
    private  IGroupStrategy<IPeriod,Transaction> groupStrategy;// = new TransactionPeriodGroupStrategy();

    @Override
    public IncomeReportResult createReport(IncomeReportCriteria reportCriteria, Object data) {
        IncomeReportResult result = new IncomeReportResult();
        List<Transaction> transactions = (List<Transaction>) data;

        GroupContainer<IPeriod,Transaction> groupContainer = groupStrategy.group(reportCriteria,transactions);

        //aggregate total amounts
        BigDecimal totalIncome =BigDecimal.ZERO;
        for(Group<IPeriod, Transaction> group : groupContainer.getGroupList()){
            BigDecimal income = BigDecimal.ZERO;

            for(Transaction t : group.getItems()){
                if(t.getDestinationAccount().getType().equals(AccountTypeEnum.OUTCOME))
                    income = income.add(t.getAmount());

            }

            totalIncome = totalIncome.add(income);
            result.addResultEntry(income.doubleValue(),0,group.getKey().getStartPoint(),group.getKey().getEndPoint());
        }

        result.setTotalIncome(totalIncome.doubleValue());
        return result;
    }

}

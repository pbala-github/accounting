package plb.accounting.services.impl.reporting;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.dto.reporting.BalanceReportCriteria;
import plb.accounting.dto.reporting.BalanceReportResult;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.*;

/**
 * User: pbala
 * Date: 11/10/12 11:16 PM
 */
public class PeriodBalanceReportStrategy implements IReportStrategy<BalanceReportResult,BalanceReportCriteria>{

    @Override
    public BalanceReportResult createReport(BalanceReportCriteria reportCriteria, Object data) {

        BalanceReportResult result = new BalanceReportResult();
        List<Transaction> transactions = (List<Transaction>) data;

         GroupContainer<String,Transaction> groupContainer = new GroupContainer<String, Transaction>();
        //group data
        for(Transaction t : transactions){

            String periodIdentifier = getPeriodIdentifier(t.getExecutionDate());
            Group<String, Transaction> group = groupContainer.getGroupWithKey(periodIdentifier, true);

            group.addItem(t);

        }

        //aggregate total amounts
        for(Group<String, Transaction> group : groupContainer.getGroupList()){
            BigDecimal income =BigDecimal.ZERO, outcome = BigDecimal.ZERO;

            for(Transaction t : group.getItems()){
                if(t.getDestinationAccount().getType().equals(AccountTypeEnum.OUTCOME))
                    outcome = outcome.add(t.getAmount());
                else
                    income = income.add(t.getAmount());
            }

            Date period = getPeriod(group.getKey());
            result.addResultEntry(income.doubleValue(),outcome.doubleValue(),period,period);
        }

        return result;  
    }

    private Date getPeriod(String p) {
        String[] tokens = p.split("_");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,Integer.valueOf(tokens[0]));
        calendar.set(Calendar.YEAR,Integer.valueOf(tokens[1]));
        calendar.set(Calendar.DAY_OF_MONTH,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        return calendar.getTime();
    }

    private String getPeriodIdentifier(Date executionDate) {
        return executionDate.getMonth() + "_" + executionDate.getYear();
    }

}

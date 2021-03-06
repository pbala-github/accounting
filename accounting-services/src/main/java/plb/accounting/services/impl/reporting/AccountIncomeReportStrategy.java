package plb.accounting.services.impl.reporting;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.reporting.IncomeReportCriteria;
import plb.accounting.dto.reporting.IncomeReportResult;
import plb.accounting.model.Account;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 1:07 PM
 */
public class AccountIncomeReportStrategy implements IReportStrategy<IncomeReportResult,IncomeReportCriteria>{

    protected ITransformationService transformationService;

    @Override
    public IncomeReportResult createReport(IncomeReportCriteria reportCriteria, Object data) {

        IncomeReportResult result = new IncomeReportResult();
        List<Transaction> transactions = (List<Transaction>) data;

        IGroupStrategy<Account,Transaction> groupStrategy = new TransactionAccountGroupStrategy();

        GroupContainer<Account,Transaction> groupContainer = groupStrategy.group(reportCriteria,transactions);

        //aggregate total amounts
        BigDecimal totalIncome =BigDecimal.ZERO;
        for(Group<Account, Transaction> group : groupContainer.getGroupList()){
            BigDecimal income = BigDecimal.ZERO;

            for(Transaction t : group.getItems()){
                if(t.getDestinationAccount().getType().equals(AccountTypeEnum.INCOME))
                    income = income.add(t.getAmount());
            }

            totalIncome = totalIncome.add(income);
            result.addResultEntry(income.doubleValue(),0,transformationService.transform(group.getKey(), AccountDTO.class));
        }

        result.setTotalIncome(totalIncome.doubleValue());
        return result;

    }

}

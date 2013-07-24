package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.BalanceReportCriteria;
import plb.accounting.dto.reporting.BalanceReportResult;
import plb.accounting.dto.reporting.IGroupingReportCriteria;
import plb.accounting.dto.reporting.ReportCriteria;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.view.TransactionView;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/10/12 11:16 PM
 */
public class PeriodBalanceReportStrategy implements IReportStrategy<BalanceReportResult, BalanceReportCriteria> {

    @Inject
    IGroupStrategy<Period, TransactionView> groupStrategy;// = new TransactionPeriodGroupStrategy();

    @Override
    public BalanceReportResult createReport(BalanceReportCriteria reportCriteria, Object data) {

        BalanceReportResult result = new BalanceReportResult(reportCriteria);
        List<TransactionView> transactions = (List<TransactionView>) data;

        GroupContainer<Period, TransactionView> groupContainer = groupStrategy.group(reportCriteria, transactions);

        //aggregate total amounts
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalOutcome = BigDecimal.ZERO;
        for (Group<Period, TransactionView> group : groupContainer.getGroupList()) {
            BigDecimal income = BigDecimal.ZERO, outcome = BigDecimal.ZERO;

            for (TransactionView t : group.getItems()) {
                if (t.getDestinationAccountType().equals(AccountTypeEnum.OUTCOME))  //outcome transaction
                    outcome = outcome.add(t.getAmount());
                else if (t.getDestinationAccountType().equals(AccountTypeEnum.STORAGE)       //income transaction (do not include transfers among storage accounts)
                        && !t.getOriginAccountType().equals(AccountTypeEnum.STORAGE))
                    income = income.add(t.getAmount());
            }

            result.addResultEntry(income.doubleValue(), outcome.doubleValue(), group.getKey().getStartPoint(), group.getKey().getEndPoint());
            totalIncome = totalIncome.add(income);
            totalOutcome = totalOutcome.add(outcome);
        }

        result.setTotalIncome(totalIncome.doubleValue());
        result.setTotalOutcome(totalOutcome.doubleValue());
        return result;
    }


    @Override
    public boolean supports(ReportCriteria reportCriteria) {
        if (BalanceReportCriteria.class.isAssignableFrom(reportCriteria.getClass()) &&
                IGroupingReportCriteria.GroupType.PERIOD.equals(((BalanceReportCriteria) reportCriteria).getGroupBy()))
            return true;

        return false;
    }


}

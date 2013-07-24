package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.IGroupingReportCriteria;
import plb.accounting.dto.reporting.IncomeReportCriteria;
import plb.accounting.dto.reporting.IncomeReportResult;
import plb.accounting.dto.reporting.ReportCriteria;
import plb.accounting.model.AccountTypeEnum;
import plb.accounting.model.view.TransactionView;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 12:58 PM
 */
public class PeriodIncomeReportStrategy implements IReportStrategy<IncomeReportResult, IncomeReportCriteria> {

    @Inject
    IGroupStrategy<Period, TransactionView> groupStrategy;// = new TransactionPeriodGroupStrategy();

    @Override
    public IncomeReportResult createReport(IncomeReportCriteria reportCriteria, Object data) {
        IncomeReportResult result = new IncomeReportResult(reportCriteria);
        List<TransactionView> transactions = (List<TransactionView>) data;

        GroupContainer<Period, TransactionView> groupContainer = groupStrategy.group(reportCriteria, transactions);

        //aggregate total amounts
        BigDecimal totalIncome = BigDecimal.ZERO;
        for (Group<Period, TransactionView> group : groupContainer.getGroupList()) {
            BigDecimal income = BigDecimal.ZERO;

            for (TransactionView t : group.getItems()) {
                if (t.getDestinationAccountType().equals(AccountTypeEnum.STORAGE) && (t.getOriginAccountType().equals(AccountTypeEnum.INCOME) || t.getOriginAccountType().equals(AccountTypeEnum.OUTCOME)))
                    income = income.add(t.getAmount());

            }

            totalIncome = totalIncome.add(income);
            result.addResultEntry(income.doubleValue(), 0, group.getKey().getStartPoint(), group.getKey().getEndPoint());
        }

        result.setTotalIncome(totalIncome.doubleValue());
        return result;
    }

    @Override
    public boolean supports(ReportCriteria reportCriteria) {
        if (IncomeReportCriteria.class.isAssignableFrom(reportCriteria.getClass()) &&
                IGroupingReportCriteria.GroupType.PERIOD.equals(((IncomeReportCriteria) reportCriteria).getGroupBy()))
            return true;

        return false;
    }

}

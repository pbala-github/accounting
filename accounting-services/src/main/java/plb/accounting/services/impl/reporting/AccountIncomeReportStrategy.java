package plb.accounting.services.impl.reporting;

import plb.accounting.common.transformation.ITransformationService;
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
 * Date: 11/14/12 1:07 PM
 */
public class AccountIncomeReportStrategy implements IReportStrategy<IncomeReportResult, IncomeReportCriteria> {

    @Inject
    protected ITransformationService transformationService;

    @Inject
    IGroupStrategy<AccountGroupKey, TransactionView> groupStrategy;

    @Override
    public IncomeReportResult createReport(IncomeReportCriteria reportCriteria, Object data) {

        IncomeReportResult result = new IncomeReportResult(reportCriteria);
        List<TransactionView> transactions = (List<TransactionView>) data;

        GroupContainer<AccountGroupKey, TransactionView> groupContainer = groupStrategy.group(reportCriteria, transactions);

        //aggregate total amounts
        BigDecimal totalIncome = BigDecimal.ZERO;
        for (Group<AccountGroupKey, TransactionView> group : groupContainer.getGroupList()) {
            BigDecimal income = BigDecimal.ZERO;

            for (TransactionView t : group.getItems()) {
                if (group.getKey().getAccountDbId() == t.getDestinationAccountDbId()) {
                    income = income.add(t.getAmount());
                    if (t.getDestinationAccountType().equals(AccountTypeEnum.STORAGE)       //income transaction (do not include transfers among storage accounts)
                            && !t.getOriginAccountType().equals(AccountTypeEnum.STORAGE)) {
                        totalIncome = totalIncome.add(t.getAmount());
                    }
                }
            }
            result.addResultEntry(income.doubleValue(), 0, group.getKey().getAccountDbId(), group.getKey().getAccountName());
        }

        result.setTotalIncome(totalIncome.doubleValue());
        return result;

    }

    @Override
    public boolean supports(ReportCriteria reportCriteria) {
        if (IncomeReportCriteria.class.isAssignableFrom(reportCriteria.getClass()) &&
                IGroupingReportCriteria.GroupType.ACCOUNT.equals(((IncomeReportCriteria) reportCriteria).getGroupBy()))
            return true;

        return false;
    }

}

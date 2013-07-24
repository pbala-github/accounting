package plb.accounting.services.impl.reporting;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.reporting.BalanceReportCriteria;
import plb.accounting.dto.reporting.BalanceReportResult;
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
public class AccountBalanceReportStrategy implements IReportStrategy<BalanceReportResult, BalanceReportCriteria> {

    @Inject
    protected ITransformationService transformationService;

    @Inject
    IGroupStrategy<AccountGroupKey, TransactionView> groupStrategy;

    @Override
    public BalanceReportResult createReport(BalanceReportCriteria reportCriteria, Object data) {

        BalanceReportResult result = new BalanceReportResult(reportCriteria);
        List<TransactionView> transactions = (List<TransactionView>) data;

        GroupContainer<AccountGroupKey, TransactionView> groupContainer = groupStrategy.group(reportCriteria, transactions);

        //aggregate total amounts
        BigDecimal totalIncome = BigDecimal.ZERO;
        BigDecimal totalOutcome = BigDecimal.ZERO;
        for (Group<AccountGroupKey, TransactionView> group : groupContainer.getGroupList()) {

            BigDecimal income = BigDecimal.ZERO;
            BigDecimal outcome = BigDecimal.ZERO;

            for (TransactionView t : group.getItems()) {
                //account related totals
                if (group.getKey().getAccountDbId() == t.getOriginAccountDbId()) {
                    outcome = outcome.add(t.getAmount());
                    //global totals
                    if (t.getDestinationAccountType().equals(AccountTypeEnum.OUTCOME)) {
                        totalOutcome = totalOutcome.add(t.getAmount());
                    } else if (t.getDestinationAccountType().equals(AccountTypeEnum.STORAGE)       //income transaction (do not include transfers among storage accounts)
                            && !t.getOriginAccountType().equals(AccountTypeEnum.STORAGE)) {
                        totalIncome = totalIncome.add(t.getAmount());
                    }
                } else {
                    income = income.add(t.getAmount());
                }
            }

            result.addResultEntry(income.doubleValue(), outcome.doubleValue(), group.getKey().getAccountDbId(), group.getKey().getAccountName());
        }

        result.setTotalIncome(totalIncome.doubleValue());
        result.setTotalOutcome(totalOutcome.doubleValue());
        return result;
    }

    @Override
    public boolean supports(ReportCriteria reportCriteria) {
        if (BalanceReportCriteria.class.isAssignableFrom(reportCriteria.getClass()) &&
                BalanceReportCriteria.GroupType.ACCOUNT.equals(((BalanceReportCriteria) reportCriteria).getGroupBy()))
            return true;

        return false;
    }

}

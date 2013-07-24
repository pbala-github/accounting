package plb.accounting.services.impl.reporting;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.reporting.IGroupingReportCriteria;
import plb.accounting.dto.reporting.OutcomeReportCriteria;
import plb.accounting.dto.reporting.OutcomeReportResult;
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
public class AccountOutcomeReportStrategy implements IReportStrategy<OutcomeReportResult, OutcomeReportCriteria> {

    @Inject
    protected ITransformationService transformationService;

    @Inject
    IGroupStrategy<AccountGroupKey, TransactionView> groupStrategy;

    @Override
    public OutcomeReportResult createReport(OutcomeReportCriteria reportCriteria, Object data) {

        OutcomeReportResult result = new OutcomeReportResult(reportCriteria);
        List<TransactionView> transactions = (List<TransactionView>) data;

        GroupContainer<AccountGroupKey, TransactionView> groupContainer = groupStrategy.group(reportCriteria, transactions);

        //aggregate total amounts
        BigDecimal totalOutcome = BigDecimal.ZERO;
        for (Group<AccountGroupKey, TransactionView> group : groupContainer.getGroupList()) {
            BigDecimal outcome = BigDecimal.ZERO;

            String accountName = null;
            for (TransactionView t : group.getItems()) {
                if (group.getKey().getAccountDbId() == t.getOriginAccountDbId()) {
                    outcome = outcome.add(t.getAmount());
                    if (t.getDestinationAccountType().equals(AccountTypeEnum.OUTCOME)) {
                        totalOutcome = totalOutcome.add(t.getAmount());
                    }
                }
            }

            result.addResultEntry(0, outcome.doubleValue(), group.getKey().getAccountDbId(), group.getKey().getAccountName());
        }

        result.setTotalOutcome(totalOutcome.doubleValue());
        return result;

    }


    @Override
    public boolean supports(ReportCriteria reportCriteria) {
        if (OutcomeReportCriteria.class.isAssignableFrom(reportCriteria.getClass()) &&
                IGroupingReportCriteria.GroupType.ACCOUNT.equals(((OutcomeReportCriteria) reportCriteria).getGroupBy()))
            return true;

        return false;
    }

}

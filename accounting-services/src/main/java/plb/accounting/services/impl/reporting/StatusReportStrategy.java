package plb.accounting.services.impl.reporting;

import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.reporting.ReportCriteria;
import plb.accounting.dto.reporting.StatusReportCriteria;
import plb.accounting.dto.reporting.StatusReportResult;
import plb.accounting.model.Account;

import java.util.List;

/**
 * User: pbala
 * Date: 3/22/13 4:25 PM
 */
public class StatusReportStrategy implements IReportStrategy<StatusReportResult, StatusReportCriteria> {

    @Override
    public StatusReportResult createReport(StatusReportCriteria reportCriteria, Object data) {
        List<Account> accounts = (List<Account>) data;
        StatusReportResult result = new StatusReportResult();

        for (Account account : accounts) {
            result.addResultEntry(getAccountDTO(account));
        }

        return result;
    }

    @Override
    public boolean supports(ReportCriteria reportCriteria) {
        return reportCriteria != null && StatusReportCriteria.class.isAssignableFrom(reportCriteria.getClass());
    }

    private AccountDTO getAccountDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setCurrentBalance(account.getCurrentBalance());
        accountDTO.setName(account.getName());
        accountDTO.setId(account.getId());
        accountDTO.setInitialBalance(account.getInitialBalance());

        return accountDTO;
    }
}

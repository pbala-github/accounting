package plb.accounting.web.controllers;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.dto.reporting.StatusReportCriteria;
import plb.accounting.dto.reporting.StatusReportResult;
import plb.accounting.services.AccountingService;
import plb.accounting.web.qualifiers.WebResource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * User: pbala
 * Date: 3/20/13 12:27 PM
 */
@Named
@ApplicationScoped
public class AccountController {

    @Inject
    @WebResource
    private AccountingService service;

    public List<BaseAccountInfoDTO> getAccounts(AccountSearchCriteria searchCriteria) {
        return service.searchAccounts(searchCriteria);
    }

    public StatusReportResult getStatusReportResult(StatusReportCriteria criteria) {
        return service.createStatusReport(criteria);
    }

    public  List<AccountDTO> getAccountsTree() {
        return service.getAccountsTree();
    }

    public BaseAccountInfoDTO saveAccount(BaseAccountInfoDTO accountInfoDTO) {
        return service.saveAccount(accountInfoDTO);
    }

    public List<BaseAccountInfoDTO> getAllAccounts() {
        return service.getAccounts();
    }

}

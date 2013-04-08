package plb.accounting.web.viewmodels;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.AccountTypeEnum;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.dto.reporting.BaseReportResult;
import plb.accounting.dto.reporting.StatusReportCriteria;
import plb.accounting.web.controllers.AccountController;
import plb.accounting.web.qualifiers.RequestParam;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

/**
 * User: pbala
 * Date: 3/20/13 12:42 PM
 */
@Named
@RequestScoped
public class AccountsView {

    @Inject
    private AccountController controller;

    private AccountSearchCriteria searchCriteria = new AccountSearchCriteria();

    @Inject
    private AccountWM accountWM;

    @Inject
    @RequestParam("accountId")
    private Long accountId;

//    @Inject
//    private StatusReportCriteria statusReportCriteria;

    @Produces
    @Named("accountsResult")
    public List<BaseAccountInfoDTO> searchAccounts() {
        return controller.getAccounts(searchCriteria);
    }

    @Produces
    @Named("accSearchCriteria")
    public AccountSearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public List<AccountDTO> getAccountsTree() {
        return controller.getAccountsTree();
    }

    public String saveAccount() {
        DetailedAccountDTO account = accountWM.getAccountDto();
        if (account.getParentAccount() != null && account.getParentAccount().getId() == null) {
            account.setParentAccount(null);
        }

        controller.saveAccount(account);
        accountWM.setReadOnly(true);
        accountWM.setAccountDto(controller.loadAccount(account.getId()));
        return "viewAccount";
    }

    @Produces
    @Named("accountsReport")
    public List<BaseReportResult<StatusReportCriteria>.BaseReportResultEntry> getAccountsReport() {
        return controller.getStatusReportResult(new StatusReportCriteria()).getResultEntries();
    }

    @Produces
    @Named("accountTypes")
    public List<AccountTypeEnum> getAccountTypes() {
        return Arrays.asList(AccountTypeEnum.values());
    }

    @Produces
    @Named("accounts")
    public List<BaseAccountInfoDTO> getAccounts() {
        return controller.getAllAccounts();
    }

    public String selectAccount() {
        accountWM.setAccountDto(controller.loadAccount(accountId));
        accountWM.setReadOnly(true);
        return "viewAccount";
    }

    public String editAccount() {
        accountWM.setAccountDto(controller.loadAccount(accountId));
        accountWM.setReadOnly(false);
        return "editAccount";
    }

}

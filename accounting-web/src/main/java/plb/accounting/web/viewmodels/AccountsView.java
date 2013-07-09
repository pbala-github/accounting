package plb.accounting.web.viewmodels;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.AccountDTO;
import plb.accounting.dto.AccountTypeEnum;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.dto.DetailedAccountDTO;
import plb.accounting.dto.reporting.BaseReportResult;
import plb.accounting.dto.reporting.StatusReportCriteria;
import plb.accounting.web.ExceptionHandlingHelper;
import plb.accounting.web.controllers.AccountController;
import plb.accounting.web.jsf.ViewScoped;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;

/**
 * User: pbala
 * Date: 3/20/13 12:42 PM
 */
@Named
@ViewScoped
public class AccountsView {

    @Inject
    private AccountController controller;
    /**
     *
     */
    private AccountSearchCriteria searchCriteria;

    @Inject
    private AccountWM accountWM;
    /**
     *
     */
    private ListDataModel<BaseAccountInfoDTO> accounts;
    /**
     *
     */
    private List<AccountTypeEnum> accountTypes;

//    @Inject
//    private StatusReportCriteria statusReportCriteria;


    /**
     *
     */
    public void searchAccounts() {
        accounts = new ListDataModel<BaseAccountInfoDTO>(controller.getAccounts(searchCriteria));
    }

    @Produces
    @Named("accSearchCriteria")
    public AccountSearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public List<AccountDTO> getAccountsTree() {
        return controller.getAccountsTree();
    }

    /**
     *
     * @return
     */
    public String saveAccount() {
        DetailedAccountDTO account = accountWM.getAccountDto();
        if (account.getParentAccount() != null && account.getParentAccount().getId() == null) {
            account.setParentAccount(null);
        }

        try {
            BaseAccountInfoDTO baseAccountInfoDTO = controller.saveAccount(account);
            accountWM.setAccountDto(controller.loadAccount(baseAccountInfoDTO.getId()));
        } catch (Exception e) {
            ExceptionHandlingHelper.populateErrors(e);
            return null;
        }

        accountWM.setReadOnly(true);
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
        return accountTypes;
    }

    @Produces
    @Named("accounts")
    public ListDataModel<BaseAccountInfoDTO> getAccounts() {
        return accounts;
    }

    /**
     * @return
     */
    public String selectAccount() {
        accountWM.setAccountDto(controller.loadAccount(accounts.getRowData().getId()));
        accountWM.setReadOnly(true);
        return "viewAccount";
    }

    /**
     * @return
     */
    public String editAccount() {
        accountWM.setAccountDto(controller.loadAccount(accountWM.getAccountDto().getId()));
        accountWM.setReadOnly(false);
        return "editAccount";
    }


    @PostConstruct
    protected void initialize() {
        searchCriteria = new AccountSearchCriteria();
        accounts = new ListDataModel<BaseAccountInfoDTO>();
        accountTypes = Arrays.asList(AccountTypeEnum.values());
    }
}

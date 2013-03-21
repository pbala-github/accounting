package plb.accounting.web.viewmodels;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.web.controllers.AccountController;

import javax.enterprise.inject.New;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
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

    @Inject
    private AccountSearchCriteria searchCriteria;

    public List<BaseAccountDTO> getAccounts() {
        return controller.getAccounts(searchCriteria);
    }

    public AccountSearchCriteria getSearchCriteria() {
        return searchCriteria;
    }
}

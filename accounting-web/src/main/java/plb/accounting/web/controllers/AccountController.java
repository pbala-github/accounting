package plb.accounting.web.controllers;

import plb.accounting.common.search.AccountSearchCriteria;
import plb.accounting.dto.BaseAccountDTO;
import plb.accounting.services.AccountingService;
import plb.accounting.web.WebResource;

import javax.ejb.EJB;
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

    public List<BaseAccountDTO> getAccounts(AccountSearchCriteria searchCriteria) {
        return service.searchAccounts(searchCriteria);
    }
}

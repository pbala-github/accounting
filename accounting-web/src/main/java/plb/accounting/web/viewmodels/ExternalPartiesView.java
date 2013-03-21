package plb.accounting.web.viewmodels;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.services.AccountingService;
import plb.accounting.web.WebResource;
import plb.accounting.web.controllers.ExternalPartyController;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: pbala
 * Date: 3/21/13 10:24 PM
 */
@Named("epView")
@ViewScoped
public class ExternalPartiesView {

    @Inject
    private ExternalPartySearchCriteria searchCriteria;

    @Inject
    private ExternalPartyController controller;


    public ExternalPartySearchCriteria getSearchCriteria() {
        return searchCriteria;
    }
}

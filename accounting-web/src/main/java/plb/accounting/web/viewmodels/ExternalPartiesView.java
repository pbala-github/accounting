package plb.accounting.web.viewmodels;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;
import plb.accounting.web.controllers.ExternalPartyController;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * User: pbala
 * Date: 3/21/13 10:24 PM
 */
@Named("epView")
@RequestScoped
public class ExternalPartiesView {

    @Inject
    @Named("epSearchCriteria")
    private ExternalPartySearchCriteria searchCriteria;

    @Inject
    private transient ExternalPartyController controller;

    private DataModel<BaseExternalPartyDTO> externalParties;

    private BaseExternalPartyDTO externalParty;

    /**
     *
     */
    public void loadExternalParties() {
        externalParties = new ListDataModel<BaseExternalPartyDTO>(controller.searchExternalParties(searchCriteria));
    }

    /**
     * @return
     */
    public String saveExternalParty() {
        externalParty = controller.saveExternalParty(externalParty);
        externalParty = controller.findExternalPartyById(externalParty.getId());
        return "externalPartyInfo";
    }

    public String createExternalParty() {
        externalParty = new ExternalPartyDTO();
        return "editExternalParty";
    }

    @Produces
    @Named("epSearchCriteria")
    @RequestScoped
    public ExternalPartySearchCriteria getSearchCriteria() {
        return new ExternalPartySearchCriteria();
    }

    @Produces
    @Named("externalParties")
    public DataModel<BaseExternalPartyDTO> getExternalParties() {
        if (externalParties == null) {
            externalParties = new ListDataModel<BaseExternalPartyDTO>(controller.getExternalParties());
        }
        return externalParties;
    }

    public String selectExternalParty() {
        externalParty = controller.findExternalPartyById(new Long(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("epId")));
        return "externalPartyInfo";
    }

    public String editExternalParty() {
        externalParty = controller.findExternalPartyById(externalParty.getId());
        return "editExternalParty";
    }

    @Produces
    @Named("externalParty")
    public BaseExternalPartyDTO getExternalParty() {
        if (externalParty == null) {
            externalParty = new ExternalPartyDTO();
        }

        return externalParty;
    }
}
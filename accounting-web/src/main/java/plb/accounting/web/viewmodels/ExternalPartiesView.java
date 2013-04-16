package plb.accounting.web.viewmodels;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;
import plb.accounting.web.ExceptionHandlingHelper;
import plb.accounting.web.controllers.ExternalPartyController;
import plb.accounting.web.qualifiers.RequestParam;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
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

    @Inject
    @RequestParam("epId")
    private Long externalPartyId;

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
        try {
            externalParty = controller.saveExternalParty(externalParty);
        } catch (Exception e) {
            ExceptionHandlingHelper.populateErrors(e);
            return null;
        }

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
        externalParty = controller.findExternalPartyById(externalPartyId);
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
            if (externalPartyId != null) {
                externalParty = controller.findExternalPartyById(externalPartyId);
            } else {
                externalParty = new ExternalPartyDTO();
            }
        }

        return externalParty;
    }
}

package plb.accounting.web.viewmodels;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.web.controllers.ExternalPartyController;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * User: pbala
 * Date: 3/21/13 10:24 PM
 */
@Named("epView")
@SessionScoped
public class ExternalPartiesView implements Serializable {

    @Inject
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
     *
     * @return
     */
    public String saveExternalParty() {
        externalParty = controller.saveExternalParty(externalParty);
        return "externalPartyInfo";
    }
    
    public String createExternalParty() {
        externalParty = new BaseExternalPartyDTO();
        return "editExternalParty";
    }

    /**
     *
     * @return
     */
    public ExternalPartySearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    /**
     *
     * @return
     */
    public DataModel<BaseExternalPartyDTO> getExternalParties() {
        return externalParties;
    }

    public String selectExternalParty() {
        externalParty = controller.findExternalPartyById(externalParties.getRowData().getId());
        return "externalPartyInfo";
    }

    public BaseExternalPartyDTO getExternalParty() {
        return externalParty;
    }
}

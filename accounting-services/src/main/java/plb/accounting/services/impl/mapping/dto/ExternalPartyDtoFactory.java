package plb.accounting.services.impl.mapping.dto;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.view.ExternalPartyView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author: pbala
 */
@ApplicationScoped
public class ExternalPartyDtoFactory {

    @Inject
    protected ITransformationService transformationService;

    /**
     * @param externalPartyView
     * @return
     */
    public BaseExternalPartyDTO toBaseDto(ExternalPartyView externalPartyView) {
        return transformationService.transform(externalPartyView, BaseExternalPartyDTO.class);
    }

    /**
     * @param externalParty
     * @return
     */
    public ExternalPartyDTO toDto(ExternalParty externalParty) {
        return transformationService.transform(externalParty, ExternalPartyDTO.class);
    }

    /**
     * @param externalParty
     * @return
     */
    public BaseExternalPartyDTO toBaseDto(ExternalParty externalParty) {
        return transformationService.transform(externalParty, BaseExternalPartyDTO.class);
    }
}

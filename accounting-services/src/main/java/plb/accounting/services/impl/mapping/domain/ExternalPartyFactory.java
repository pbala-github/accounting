package plb.accounting.services.impl.mapping.domain;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.model.ExternalParty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author: pbala
 */
@ApplicationScoped
public class ExternalPartyFactory {

    @Inject
    private ITransformationService transformationService;

    public ExternalParty toDomainObject(BaseExternalPartyDTO organization) {
        return transformationService.transform(organization, ExternalParty.class);
    }
}

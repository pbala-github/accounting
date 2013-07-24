package plb.accounting.services.impl.mapping;

import plb.accounting.common.transformation.AccountingObjectProviderSupport;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;
import plb.accounting.model.ExternalParty;

/**
 * @author pbala
 */
public class ExternalPartyProvider extends AccountingObjectProviderSupport<ExternalParty> {

    @Override
    public Class<?>[] getSourceTypes() {
        return new Class<?>[]{BaseExternalPartyDTO.class, ExternalPartyDTO.class};
    }

    @Override
    public ExternalParty get(ProvisionRequest<ExternalParty> request) {
        BaseExternalPartyDTO externalPartyDTO = (BaseExternalPartyDTO) request.getSource();

        return new ExternalParty(externalPartyDTO.getName());
    }
}

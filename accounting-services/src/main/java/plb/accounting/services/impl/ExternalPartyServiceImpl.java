package plb.accounting.services.impl;

import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.common.utils.Assert;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.ExternalPartyDTO;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.view.ExternalPartyView;
import plb.accounting.services.ExternalPartyService;
import plb.accounting.services.impl.intercept.Validate;
import plb.accounting.services.impl.mapping.domain.ExternalPartyFactory;
import plb.accounting.services.impl.mapping.dto.ExternalPartyDtoFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:24 PM
 */
public class ExternalPartyServiceImpl implements ExternalPartyService {

    @EJB
    private ExternalPartyDAO dao;

    @Inject
    private ExternalPartyDtoFactory dtoFactory;

    @Inject
    private ExternalPartyFactory domainFactory;

    /**
     * @param organizationId
     * @return
     */
    @Override
    public ExternalPartyDTO findExternalPartyById(long organizationId) {
        Assert.isTrue(organizationId > 0);
        ExternalParty externalParty = dao.findById(ExternalParty.class, organizationId);
        return externalParty != null ? dtoFactory.toDto(externalParty) : null;
    }

    /**
     * @param externalPartyDto
     * @return
     */
    @Validate
    @Override
    public BaseExternalPartyDTO saveExternalParty(BaseExternalPartyDTO externalPartyDto) {
        Assert.notNull(externalPartyDto);
        ExternalParty externalParty = domainFactory.toDomainObject(externalPartyDto);
        externalParty = dao.saveOrUpdate(externalParty);
        return dtoFactory.toBaseDto(externalParty);
    }

    /**
     * @param organizationId
     */
    @Override
    public void deleteExternalParty(long organizationId) {
        Assert.isTrue(organizationId > 0);
        dao.delete(ExternalParty.class, organizationId);
    }

    /**
     * @param criteria
     * @return
     */
    @Override
    public List<BaseExternalPartyDTO> searchExternalParties(ExternalPartySearchCriteria criteria) {
        Assert.notNull(criteria);
        List<BaseExternalPartyDTO> externalPartyDTOList = new ArrayList<BaseExternalPartyDTO>();
        List<ExternalPartyView> externalPartyViewList = dao.searchExternalParties(criteria);

        for (ExternalPartyView externalPartyView : externalPartyViewList) {
            externalPartyDTOList.add(dtoFactory.toBaseDto(externalPartyView));
        }
        return externalPartyDTOList;
    }
}

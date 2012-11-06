package plb.accounting.services;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dao.IAccountingDAOFacade;

/**
 * User: pbala
 * Date: 11/6/12 4:02 PM
 */
public abstract class BaseService {

    /**
     *
     */
    protected ITransformationService transformationService;

    /**
     *
     */
    protected IAccountingDAOFacade accountingDAOFacade;


    public void setTransformationService(ITransformationService transformationService) {
        this.transformationService = transformationService;
    }

    public void setAccountingDAOFacade(IAccountingDAOFacade accountingDAOFacade) {
        this.accountingDAOFacade = accountingDAOFacade;
    }
}

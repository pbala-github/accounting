package plb.accounting.services.impl;

import plb.accounting.common.transformation.ITransformationService;

import javax.inject.Inject;

/**
 * User: pbala
 * Date: 11/6/12 4:02 PM
 */
public abstract class BaseService {

    @Inject
    protected ITransformationService transformationService;

}

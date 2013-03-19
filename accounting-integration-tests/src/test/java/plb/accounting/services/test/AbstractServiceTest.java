package plb.accounting.services.test;

import plb.accounting.dao.test.DataBootstrap;
import plb.accounting.services.AccountingService;

import javax.ejb.EJB;
import javax.inject.Inject;

/**
 * User: pbala
 * Date: 3/17/13 11:32 PM
 */
public abstract class AbstractServiceTest {

    @Inject
    DataBootstrap dataBootstrap;

    @EJB
    AccountingService service;
}

package plb.accounting.web;

import plb.accounting.services.AccountingService;
import plb.accounting.web.qualifiers.WebResource;

import javax.ejb.EJB;
import javax.enterprise.inject.Produces;

/**
 * User: pbala
 * Date: 3/21/13 10:10 PM
 */
public class Resources {

    @Produces @WebResource
    @EJB
    private AccountingService service;
}

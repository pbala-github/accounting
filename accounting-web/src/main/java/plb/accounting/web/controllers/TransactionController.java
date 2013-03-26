package plb.accounting.web.controllers;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.services.AccountingService;
import plb.accounting.web.qualifiers.WebResource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * User: pbala
 * Date: 3/26/13 8:38 AM
 */
@Named
@ApplicationScoped
public class TransactionController {

    @Inject
    @WebResource
    private AccountingService service;

    public List<TransactionDTO> getTransactions(TransactionSearchCriteria criteria) {
        return service.searchTransactions(criteria);
    }

    public TransactionDTO loadTransaction(Long transactionId) {
        return service.findTransactionById(transactionId);
    }

    public TransactionDTO saveTransaction(TransactionDTO transaction) {
        return service.saveTransaction(transaction);
    }
}

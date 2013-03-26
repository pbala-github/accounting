package plb.accounting.web.viewmodels;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.BaseAccountInfoDTO;
import plb.accounting.dto.BaseExternalPartyDTO;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.web.WebHelper;
import plb.accounting.web.controllers.TransactionController;
import plb.accounting.web.qualifiers.RequestParam;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * User: pbala
 * Date: 3/26/13 8:36 AM
 */
@Named
@RequestScoped
public class TransactionsView {
    @Inject
    private TransactionController controller;
    
    @Inject
    private TransactionSearchCriteria searchCriteria;

    private TransactionDTO transaction;

    @Inject
    @RequestParam("transactionId")
    private Long transactionId;

    @Produces
    @Named("transactions")
    public List<TransactionDTO> getTransactions() {
        return controller.getTransactions(searchCriteria);
    }
    
    public String selectTransaction() {
        transaction = controller.loadTransaction(transactionId);
        return "editTransaction";
    }

    @Produces
    @Named("transaction")
    public TransactionDTO getTransaction() {
        if (transaction == null) {
            transaction = new TransactionDTO();
            transaction.setOriginAccount(new BaseAccountInfoDTO());
            transaction.setDestinationAccount(new BaseAccountInfoDTO());
            transaction.setRelatedParty(new BaseExternalPartyDTO());
        }

        return transaction;
    }

    public String saveTransaction() {
        transaction = controller.saveTransaction(transaction);
        return "editTransaction";
    }
}

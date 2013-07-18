package plb.accounting.services.impl.mapping.dto;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.model.Transaction;
import plb.accounting.model.view.TransactionView;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author: pbala
 */
@ApplicationScoped
public class TransactionDtoFactory {

    @Inject
    protected ITransformationService transformationService;

    /**
     * @param transaction
     * @return
     */
    public TransactionDTO toDto(Transaction transaction) {
        return transformationService.transform(transaction, TransactionDTO.class);
    }

    /**
     * @param transactionView
     * @return
     */
    public TransactionDTO toDto(TransactionView transactionView) {
        return transformationService.transform(transactionView, TransactionDTO.class);
    }
}

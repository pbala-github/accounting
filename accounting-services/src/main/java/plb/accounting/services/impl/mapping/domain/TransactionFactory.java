package plb.accounting.services.impl.mapping.domain;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.model.Transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author: pbala
 */
@ApplicationScoped
public class TransactionFactory {

    @Inject
    protected ITransformationService transformationService;

    /**
     * @param transactionDTO
     * @return
     */
    public Transaction toDomainObject(TransactionDTO transactionDTO) {
        return transformationService.transform(transactionDTO, Transaction.class);
    }
}

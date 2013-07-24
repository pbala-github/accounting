package plb.accounting.services.impl.mapping.domain;

import plb.accounting.common.transformation.ITransformationService;
import plb.accounting.common.utils.Assert;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author: pbala
 */
@ApplicationScoped
public class TransactionFactory {

    @EJB
    private TransactionDAO dao;

    @EJB
    private ExternalPartyDAO externalPartyDAO;

    @Inject
    protected ITransformationService transformationService;

    /**
     * @param transactionDto
     * @return
     */
    public Transaction toDomainObject(TransactionDTO transactionDto) {
        Assert.notNull(transactionDto);
        Assert.notNull(transactionDto.getOriginAccount());
        Assert.notNull(transactionDto.getDestinationAccount());
        Assert.notNull(transactionDto.getOriginAccount().getId());
        Assert.notNull(transactionDto.getDestinationAccount().getId());

        Transaction transaction = null;
        if (transactionDto.getId() != null) {
            transaction = dao.findById(Transaction.class, transactionDto.getId());
            Assert.notNull(transaction, String.format("No transaction found with id %s", transactionDto.getId()));
        }

        Account originAccount = dao.findById(Account.class, transactionDto.getOriginAccount().getId());
        Assert.notNull(originAccount, String.format("No account found with id %s", transactionDto.getOriginAccount().getId()));
        Account destinationAccount = dao.findById(Account.class, transactionDto.getDestinationAccount().getId());
        Assert.notNull(destinationAccount, String.format("No account found with id %s", transactionDto.getDestinationAccount().getId()));

        if (transaction == null) {
            transaction = new Transaction(//
                    originAccount,//
                    destinationAccount,//
                    transactionDto.getExecutionDate(),//
                    transactionDto.getAmount(),//
                    transactionDto.getDescription()
            );
        } else {
            if (!transaction.getOriginAccount().equals(originAccount)) {
                transaction.setOriginAccount(originAccount);
            }
            if (!transaction.getDestinationAccount().equals(destinationAccount)) {
                transaction.setDestinationAccount(destinationAccount);
            }
            transaction.setDescription(transactionDto.getDescription());
        }

        //set external party
        ExternalParty externalParty = null;
        if (transactionDto.getRelatedParty() != null && transactionDto.getRelatedParty().getId() != null) {
            externalParty = externalPartyDAO.findById(ExternalParty.class, transactionDto.getRelatedParty().getId());
            Assert.notNull(externalParty, String.format("No external party found with id %s", transactionDto.getRelatedParty().getId()));
        }
        transaction.setRelatedParty(externalParty);

        return transaction;
    }
}

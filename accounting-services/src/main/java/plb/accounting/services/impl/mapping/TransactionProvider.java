package plb.accounting.services.impl.mapping;

import plb.accounting.common.transformation.AccountingObjectProviderSupport;
import plb.accounting.common.utils.Assert;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.model.Account;
import plb.accounting.model.Transaction;

import javax.ejb.EJB;

/**
 * @author pbala
 */
public class TransactionProvider extends AccountingObjectProviderSupport<Transaction> {

    @EJB
    private TransactionDAO dao;

    @Override
    public Class<?>[] getSourceTypes() {
        return new Class<?>[]{TransactionDTO.class};
    }

    @Override
    public Transaction get(ProvisionRequest<Transaction> request) {
        TransactionDTO transactionDto = (TransactionDTO) request.getSource();
        Assert.notNull(transactionDto);
        Assert.notNull(transactionDto.getOriginAccount());
        Assert.notNull(transactionDto.getDestinationAccount());
        Assert.notNull(transactionDto.getOriginAccount().getId());
        Assert.notNull(transactionDto.getDestinationAccount().getId());

        Account originAccount = dao.findById(Account.class, transactionDto.getOriginAccount().getId());
        Account destinationAccount = dao.findById(Account.class, transactionDto.getDestinationAccount().getId());

        return new Transaction(//
                originAccount,//
                destinationAccount,//
                transactionDto.getExecutionDate(),//
                transactionDto.getAmount(),//
                transactionDto.getDescription()
        );
    }

}

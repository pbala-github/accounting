package plb.accounting.services.impl;

import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.common.utils.Assert;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.dto.TransactionDTO;
import plb.accounting.model.Transaction;
import plb.accounting.model.view.TransactionView;
import plb.accounting.services.TransactionService;
import plb.accounting.services.impl.intercept.Validate;
import plb.accounting.services.impl.mapping.domain.TransactionFactory;
import plb.accounting.services.impl.mapping.dto.TransactionDtoFactory;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * User: pbala
 * Date: 11/5/12 4:13 PM
 */
public class TransactionServiceImpl implements TransactionService {

    @EJB
    private TransactionDAO dao;

    @Inject
    private TransactionDtoFactory dtoFactory;

    @Inject
    private TransactionFactory domainFactory;

    /**
     * @param transactionId
     * @return
     */
    @Override
    public TransactionDTO findTransactionById(long transactionId) {
        Assert.isTrue(transactionId > 0);
        Transaction transaction = dao.findById(Transaction.class, transactionId);
        return transaction != null ? dtoFactory.toDto(transaction) : null;
    }

    /**
     * @param transactionDTO
     * @return
     */
    @Validate
    @Override
    public TransactionDTO saveTransaction(TransactionDTO transactionDTO) {
        Assert.notNull(transactionDTO);
        Transaction transaction = domainFactory.toDomainObject(transactionDTO);
        transaction = dao.saveOrUpdate(transaction);
        return dtoFactory.toDto(transaction);
    }

    /**
     * @param transactionId
     */
    @Override
    public void deleteTransaction(long transactionId) {
        Assert.isTrue(transactionId > 0);
        dao.delete(Transaction.class, transactionId);
    }

    /**
     * @param criteria
     * @return
     */
    @Override
    public List<TransactionDTO> searchTransactions(TransactionSearchCriteria criteria) {
        Assert.notNull(criteria);
        List<TransactionDTO> transactionDTOList = new ArrayList<TransactionDTO>();
        List<TransactionView> transactionViewList = dao.searchTransactions(criteria);

        for (TransactionView transactionView : transactionViewList) {
            transactionDTOList.add(dtoFactory.toDto(transactionView));
        }

        return transactionDTOList;
    }
}

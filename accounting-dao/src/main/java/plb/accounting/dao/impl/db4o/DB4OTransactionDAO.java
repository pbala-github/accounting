package plb.accounting.dao.impl.db4o;

import com.db4o.query.Predicate;
import org.apache.commons.lang.StringUtils;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dao.TransactionDAO;
import plb.accounting.model.Transaction;
import plb.accounting.model.view.TransactionView;

import javax.enterprise.inject.Alternative;
import java.util.List;

/**
 * User: pbala
 * Date: 3/11/13 2:23 PM
 */
@Alternative
public class DB4OTransactionDAO extends DB4OBaseDAO implements TransactionDAO{

    @Override
    public List<TransactionView> searchTransactions(final TransactionSearchCriteria searchCriteria) {

        Predicate predicate = new Predicate<Transaction>() {
            @Override
            public boolean match(Transaction candidate) {
                return (StringUtils.isEmpty(searchCriteria.getDescription()) ||
                        candidate.getDescription().equals(searchCriteria.getDescription())) &&
                        (searchCriteria.getAmountFrom() == null ||
                                searchCriteria.getAmountFrom().compareTo(candidate.getAmount()) <= 0) &&
                        (searchCriteria.getAmountTo() == null ||
                                searchCriteria.getAmountTo().compareTo(candidate.getAmount()) >= 0) &&
                        (searchCriteria.getOriginAccountIds().isEmpty() ||
                                searchCriteria.hasOriginAccountId(candidate.getOriginAccount().getId())) &&
                        (searchCriteria.getDestinationAccountIds().isEmpty() ||
                                searchCriteria.hasDestinationAccountId(candidate.getDestinationAccount().getId())) &&
                        (searchCriteria.getExecutionDateFrom() == null ||
                                searchCriteria.getExecutionDateFrom().compareTo(candidate.getExecutionDate()) <= 0) &&
                        (searchCriteria.getExecutionDateTo() == null ||
                                searchCriteria.getExecutionDateTo().compareTo(candidate.getExecutionDate()) >= 0) &&
                        (StringUtils.isEmpty(searchCriteria.getOrgName()) ||
                                (candidate.getRelatedParty() != null &&
                                        candidate.getRelatedParty().getName().contains(searchCriteria.getOrgName()))) &&
                        (StringUtils.isEmpty(searchCriteria.getOrgVat()) ||
                                (candidate.getRelatedParty() != null &&
                                        candidate.getRelatedParty().getVat().equals(searchCriteria.getOrgVat())));
            }
        };

        return executeQuery(predicate);
    }
}

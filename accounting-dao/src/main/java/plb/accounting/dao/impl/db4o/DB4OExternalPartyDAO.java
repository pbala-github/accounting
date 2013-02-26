package plb.accounting.dao.impl.db4o;

import com.db4o.query.Predicate;
import org.apache.commons.lang.StringUtils;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dao.IExternalPartyDAO;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 10/31/12 1:41 PM
 */
public class DB4OExternalPartyDAO extends DB4OBaseDAO<ExternalParty> implements IExternalPartyDAO{


    @Override
    public List<ExternalParty> searchExternalParties(final ExternalPartySearchCriteria searchCriteria) {
        Predicate predicate = new Predicate<ExternalParty>() {
            @Override
            public boolean match(ExternalParty candidate) {
                return (StringUtils.isEmpty(searchCriteria.getName()) ||
                        candidate.getName().toLowerCase().contains(searchCriteria.getName().toLowerCase())) &&
                        (StringUtils.isEmpty(searchCriteria.getVat()) ||
                                searchCriteria.getVat().equals(candidate.getVat())) ;
            }
        };

        return executeQuery(predicate);
    }

    @Override
    public void delete(long id) {
        ExternalParty party = findById(id);

        if(party == null)
            throw new RuntimeException("Entity not found in DB.");

        if(party.getTransactions() != null)
            for(Transaction transaction : party.getTransactions()){
                transaction.setRelatedParty(null);
                getDb().store(transaction);
            }

        getDb().delete(party);
    }

    @Override
    protected Class<ExternalParty> getObjectClass() {
        return ExternalParty.class;
    }
}

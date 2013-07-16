package plb.accounting.dao.impl.db4o;

import com.db4o.query.Predicate;
import org.apache.commons.lang.StringUtils;
import plb.accounting.common.search.ExternalPartySearchCriteria;
import plb.accounting.dao.ExternalPartyDAO;
import plb.accounting.model.ExternalParty;
import plb.accounting.model.Transaction;
import plb.accounting.model.view.ExternalPartyView;

import javax.enterprise.inject.Alternative;
import java.util.List;

/**
 * User: pbala
 * Date: 10/31/12 1:41 PM
 */
@Alternative
public class DB4OExternalPartyDAO extends DB4OBaseDAO implements ExternalPartyDAO {


    @Override
    public List<ExternalPartyView> searchExternalParties(final ExternalPartySearchCriteria searchCriteria) {
        Predicate predicate = new Predicate<ExternalParty>() {
            @Override
            public boolean match(ExternalParty candidate) {
                return (StringUtils.isEmpty(searchCriteria.getName()) ||
                        candidate.getName().toLowerCase().contains(searchCriteria.getName().toLowerCase())) &&
                        (StringUtils.isEmpty(searchCriteria.getVat()) ||
                                searchCriteria.getVat().equals(candidate.getVat()));
            }
        };

        return executeQuery(predicate);
    }

    @Override
    public List<ExternalPartyView> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void delete(long id) {
        ExternalParty party = findById(ExternalParty.class, id);

        if (party == null)
            throw new RuntimeException("Entity not found in DB.");

        if (party.getTransactions() != null)
            for (Transaction transaction : party.getTransactions()) {
                transaction.setRelatedParty(null);
                getDb().store(transaction);
            }

        getDb().delete(party);
    }


}

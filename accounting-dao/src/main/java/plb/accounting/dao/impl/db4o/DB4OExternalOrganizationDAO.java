package plb.accounting.dao.impl.db4o;

import com.db4o.query.Predicate;
import org.apache.commons.lang.StringUtils;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.dao.IExternalOrganizationDAO;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;
import plb.accounting.model.Transaction;

import java.util.List;

/**
 * User: pbala
 * Date: 10/31/12 1:41 PM
 */
public class DB4OExternalOrganizationDAO extends DB4OBaseDAO<ExternalOrganization> implements IExternalOrganizationDAO{


    @Override
    public List<ExternalOrganization> searchExternalOrganizations(final ExternalOrganizationSearchCriteria searchCriteria) {
        Predicate predicate = new Predicate<ExternalOrganization>() {
            @Override
            public boolean match(ExternalOrganization candidate) {
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
        ExternalOrganization organization = findById(id);

        if(organization == null)
            throw new RuntimeException("Entity not found in DB.");

        if(organization.getTransactions() != null)
            for(Transaction transaction : organization.getTransactions()){
                transaction.setRelatedOrganization(null);
                getDb().store(transaction);
            }

        getDb().delete(organization);
    }

    @Override
    protected Class<ExternalOrganization> getObjectClass() {
        return ExternalOrganization.class;
    }
}

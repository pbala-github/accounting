package plb.accounting.dao.impl.db4o;

import com.db4o.query.Predicate;
import org.apache.commons.lang.StringUtils;
import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.dao.IExternalOrganizationDAO;
import plb.accounting.model.Account;
import plb.accounting.model.ExternalOrganization;

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
                        candidate.getName().equals(searchCriteria.getName())) &&
                        (StringUtils.isEmpty(searchCriteria.getVat()) ||
                                searchCriteria.getVat().equals(candidate.getVat())) ;
            }
        };

        return executeQuery(predicate);
    }

    @Override
    protected Class<ExternalOrganization> getObjectClass() {
        return ExternalOrganization.class;
    }
}

package plb.accounting.dao.impl.file;

import plb.accounting.common.search.ExternalOrganizationSearchCriteria;
import plb.accounting.dao.IExternalOrganizationDAO;
import plb.accounting.model.ExternalOrganization;

import java.util.List;

/**
 * User: pbala
 * Date: 10/30/12 3:39 PM
 */
public class FileExternalOrganizationDAO implements IExternalOrganizationDAO {

    @Override
    public ExternalOrganization findById(long id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void persist(ExternalOrganization obj) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(long id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void update(ExternalOrganization obj) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ExternalOrganization> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<ExternalOrganization> searchExternalOrganizations(ExternalOrganizationSearchCriteria searchCriteria) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}

package plb.accounting.dao.dbunit;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;

import javax.persistence.EntityManager;
import java.sql.Connection;

/**
 * User: pbala
 * Date: 5/10/13 11:39 AM
 */
public class TestUtils {

    /**
     * @param em
     * @param dataSetFile
     */
    public static void seedData(EntityManager em, String dataSetFile) throws Exception {
        em.getTransaction().begin();
        Connection connection = em.unwrap(Connection.class);
        try {
            IDatabaseConnection dbUnitConnection = new DatabaseConnection(connection);
            dbUnitConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory());
            IDataSet dataSet = getDataSet(dataSetFile);
            DatabaseOperation.CLEAN_INSERT.execute(dbUnitConnection,dataSet);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.getTransaction().commit();
            connection.close();
        }
    }

    /**
     *
     * @param dataSetFile
     * @return
     */
    private static IDataSet getDataSet(String dataSetFile) {
        return null;  //To change body of created methods use File | Settings | File Templates.
    }
}

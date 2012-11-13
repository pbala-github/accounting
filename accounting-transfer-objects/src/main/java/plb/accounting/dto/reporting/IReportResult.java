package plb.accounting.dto.reporting;

/**
 * User: pbala
 * Date: 11/10/12 10:48 PM
 */
public interface IReportResult<T extends IReportCriteria> {
    
    T getReportCriteria();
    
    void setReportCriteria(T criteria);
}

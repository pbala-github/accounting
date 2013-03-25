package plb.accounting.dto.reporting;

import java.util.List;

/**
 * User: pbala
 * Date: 11/10/12 10:48 PM
 */
public interface ReportResult<T extends ReportCriteria> {
    
    T getReportCriteria();
    
    void setReportCriteria(T criteria);

    <E> List<E> getResultEntries();
}

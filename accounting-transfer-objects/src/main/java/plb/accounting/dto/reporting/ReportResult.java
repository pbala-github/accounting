package plb.accounting.dto.reporting;

import java.io.Serializable;
import java.util.List;

/**
 * User: pbala
 * Date: 11/10/12 10:48 PM
 */
public interface ReportResult<T extends ReportCriteria> extends Serializable {

    T getReportCriteria();

    <E> List<E> getResultEntries();
}

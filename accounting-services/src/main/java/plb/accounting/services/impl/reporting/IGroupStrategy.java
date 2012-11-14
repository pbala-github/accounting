package plb.accounting.services.impl.reporting;

import plb.accounting.dto.reporting.IGroupingReportCriteria;

import java.util.List;

/**
 * User: pbala
 * Date: 11/14/12 12:08 PM
 */
public interface IGroupStrategy<K,E> {
    
    GroupContainer<K,E> group(IGroupingReportCriteria criteria,List<E> data);
}

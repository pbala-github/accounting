package plb.accounting.services.impl.mapping;

import org.modelmapper.PropertyMap;
import plb.accounting.common.search.TransactionSearchCriteria;
import plb.accounting.dto.reporting.BaseGroupingReportCriteria;

/**
 * @author: pbala
 */
public class ReportCriteriaToTransactionSearchCriteriaMapper extends PropertyMap<BaseGroupingReportCriteria, TransactionSearchCriteria> {
    @Override
    protected void configure() {
        map().setExecutionDateFrom(source.getStartDate());
        map().setExecutionDateTo(source.getEndDate());
        map().setDestinationAccountIds(source.getIncludedAccountsIds());
        map().setOriginAccountIds(source.getIncludedAccountsIds());
    }
}

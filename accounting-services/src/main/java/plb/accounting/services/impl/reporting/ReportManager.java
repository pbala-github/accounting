package plb.accounting.services.impl.reporting;

import org.springframework.util.Assert;
import plb.accounting.dto.reporting.IReportCriteria;
import plb.accounting.dto.reporting.IReportResult;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

/**
 * User: pbala
 * Date: 11/10/12 11:09 PM
 */
public class ReportManager implements IReportManager {

    private Set<IReportStrategy> strategies = new HashSet<IReportStrategy>();

    @Inject
    private Instance<IReportStrategy> iReportStrategies;

    @PostConstruct
    private void initReportStrategies() {
        for (IReportStrategy iReportStrategy : iReportStrategies) {
            strategies.add(iReportStrategy);
        }
    }

    /**
     * Dispatch to the right overloaded method
     *
     * @param reportCriteria
     * @return
     */
    public <T extends IReportResult, E extends IReportCriteria> IReportStrategy<T, E> getReportStrategy(IReportCriteria reportCriteria) {

        for (IReportStrategy reportStrategy : strategies) {
            if (reportStrategy.supports(reportCriteria))
                return reportStrategy;
        }

        return null;
    }

    @Override
    public <T extends IReportResult, E extends IReportCriteria> T createReport(E criteria, Object data) {

        IReportStrategy<T, E> reportStrategy = getReportStrategy(criteria);
        Assert.notNull(reportStrategy, String.format("No report strategy found for report criteria %s", criteria));

        T result = reportStrategy.createReport(criteria, data);
        result.setReportCriteria(criteria);

        return result;
    }
}

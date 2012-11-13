package plb.accounting.dto.reporting;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * User: pbala
 * Date: 11/7/12 9:03 AM
 */
public abstract class BaseReportCriteria implements IReportCriteria{

    public enum GroupType{
        ACCOUNT,PERIOD
    }

    /**
     *
     */
    private Date startDate;

    /**
     *
     */
    private Date endDate;

    /**
     *
     */
    private Set<Long> includedAccountsIds = new HashSet<Long>();

    /**
     *
     */
    private GroupType groupBy = GroupType.PERIOD;


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public GroupType getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(GroupType groupBy) {
        this.groupBy = groupBy;
    }

    public Set<Long> getIncludedAccountsIds() {
        return includedAccountsIds;
    }

    public void setIncludedAccountsIds(Set<Long> includedAccountsIds) {
        this.includedAccountsIds = includedAccountsIds;
    }
}

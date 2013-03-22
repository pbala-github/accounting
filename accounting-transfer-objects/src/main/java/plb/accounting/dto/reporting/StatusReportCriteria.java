package plb.accounting.dto.reporting;

/**
 * User: pbala
 * Date: 3/22/13 4:09 PM
 */
public class StatusReportCriteria extends BaseGroupingReportCriteria {

    protected int maxDepth;

    @Override
    public GroupType getGroupBy() {
        return GroupType.ACCOUNT;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void setMaxDepth(int maxDepth) {
        this.maxDepth = maxDepth;
    }
}

package plb.accounting.dto.reporting;

/**
 * User: pbala
 * Date: 11/14/12 12:13 PM
 */
public interface IGroupingReportCriteria extends ReportCriteria {

    public enum GroupType{
        ACCOUNT,PERIOD
    }

    GroupType getGroupBy();

   void setGroupBy(GroupType groupBy);

}

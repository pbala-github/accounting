package plb.accounting.services.impl.reporting;

import java.util.Date;

/**
 * User: pbala
 * Date: 11/14/12 12:26 PM
 */
public class Period {

    private Date startPoint;

    private Date endPoint;

    public Period() {
    }

    public Period(Date startPoint, Date endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public Date getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Date startPoint) {
        this.startPoint = startPoint;
    }

    public Date getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Date endPoint) {
        this.endPoint = endPoint;
    }

    @Override
    public int hashCode() {
        return (startPoint.hashCode() + endPoint.hashCode());
    }

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof Period) {
            Period theOther = (Period) obj;

            return startPoint.equals(theOther.getStartPoint()) && endPoint.equals(theOther.getEndPoint());
        }

        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "Period{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}

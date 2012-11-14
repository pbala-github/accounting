package plb.accounting.services.impl.reporting;

import java.util.Date;

/**
 * User: pbala
 * Date: 11/14/12 12:21 PM
 */
public interface IPeriod {
    
    Date getStartPoint();
    
    void setStartPoint(Date startPoint);

    Date getEndPoint();

    void setEndPoint(Date endPoint);
}

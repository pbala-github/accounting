package plb.accounting.common.paging;

import java.io.Serializable;

/**
 * User: pbala
 * Date: 3/13/13 4:05 PM
 */
public class PaginationInfo implements Serializable {

    private int offset = 0;

    private int pageSize = 50;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

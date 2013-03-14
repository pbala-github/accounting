package plb.accounting.common.search;

import plb.accounting.common.paging.PaginationInfo;

/**
 * User: pbala
 * Date: 11/1/12 8:34 AM
 */
public class ExternalPartySearchCriteria extends PaginationInfo{

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String vat;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }
}

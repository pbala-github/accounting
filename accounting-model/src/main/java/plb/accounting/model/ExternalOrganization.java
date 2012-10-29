package plb.accounting.model;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: panagiotis
 * Date: 10/29/12
 * Time: 9:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExternalOrganization extends BaseEntity{

    /**
     *
     */
    private String name;

    /**
     *
     */
    private String vat;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private List<Transaction> transactions;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}

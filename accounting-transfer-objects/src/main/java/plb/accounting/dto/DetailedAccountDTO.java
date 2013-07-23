package plb.accounting.dto;

import java.util.List;

/**
 * User: pbala
 * Date: 11/6/12 9:10 AM
 */
public class DetailedAccountDTO extends AccountDTO {

    /**
     *
     */
    private List<TransactionDTO> inTransactions;

    private List<TransactionDTO> outTransactions;

    public List<TransactionDTO> getInTransactions() {
        return inTransactions;
    }

    public void setInTransactions(List<TransactionDTO> inTransactions) {
        this.inTransactions = inTransactions;
    }

    public List<TransactionDTO> getOutTransactions() {
        return outTransactions;
    }

    public void setOutTransactions(List<TransactionDTO> outTransactions) {
        this.outTransactions = outTransactions;
    }
}

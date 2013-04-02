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
    private List<TransactionDTO> transactions;

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "DetailedAccountDTO{" +
                "transactions=" + transactions +
                '}';
    }
}

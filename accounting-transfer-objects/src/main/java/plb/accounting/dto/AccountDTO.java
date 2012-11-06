package plb.accounting.dto;

import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/6/12 9:10 AM
 */
public class AccountDTO extends AbstractAccountDTO<AccountDTO>{

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

}

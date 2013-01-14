package plb.accounting.dto;

import javax.validation.Valid;
import java.util.List;

/**
 * User: pbala
 * Date: 11/6/12 9:11 AM
 */
public class ExternalOrganizationDTO extends BaseExternalOrganizationDTO{

    /**
     *
     */
    @Valid
    private List<TransactionDTO> transactions;

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }

}

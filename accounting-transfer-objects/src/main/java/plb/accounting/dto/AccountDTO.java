package plb.accounting.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * User: pbala
 * Date: 11/6/12 9:31 AM
 */
public class AccountDTO extends BaseAccountDTO {

    /**
     *
     */
    @Min(0)
    private BigDecimal currentBalance;

    /**
     *
     */
    @Valid
    private List<AccountDTO> childrenAccounts;

    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(BigDecimal currentBalance) {
        this.currentBalance = currentBalance;
    }

    public List<AccountDTO> getChildrenAccounts() {
        return childrenAccounts;
    }

    public void setChildrenAccounts(List<AccountDTO> childrenAccounts) {
        this.childrenAccounts = childrenAccounts;
    }

    @Override
    public String toString() {
        return "BaseAccountDTO{" +
                "currentBalance=" + currentBalance +
                ", childrenAccounts=" + childrenAccounts +
                "} " + super.toString();
    }
}

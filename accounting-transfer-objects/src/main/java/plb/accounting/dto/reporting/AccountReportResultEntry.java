package plb.accounting.dto.reporting;

/**
 * @author: pbala
 */
public class AccountReportResultEntry extends BaseReportResultEntry {

    /**
     *
     */
    private String accountName;
    /**
     *
     */
    private Long accountDbId;

    /**
     *
     */
    private Double balance;

    public AccountReportResultEntry(Double income, Double outcome, String accountName, Long accountDbId) {
        super(income, outcome);
        this.accountName = accountName;
        this.accountDbId = accountDbId;
    }

    public AccountReportResultEntry(String accountName, Long accountDbId, Double balance) {
        super(0.0, 0.0);
        this.accountName = accountName;
        this.accountDbId = accountDbId;
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public Long getAccountDbId() {
        return accountDbId;
    }
}


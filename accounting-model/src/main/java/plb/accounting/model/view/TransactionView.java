package plb.accounting.model.view;

import plb.accounting.model.AccountTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author: pbala
 */
public class TransactionView {

    private Long dbId;

    /**
     *
     */
    private Date executionDate;

    /**
     *
     */
    private String originAccountName;
    /**
     *
     */
    private Long originAccountDbId;
    /**
     *
     */
    private AccountTypeEnum originAccountType;

    /**
     *
     */
    private String destinationAccountName;
    /**
     *
     */
    private Long destinationAccountDbId;

    /**
     *
     */
    private AccountTypeEnum destinationAccountType;

    /**
     *
     */
    private BigDecimal amount;

    /**
     *
     */
    private String description;

    /**
     *
     */
    private String relatedPartyName;
    /**
     *
     */
    private Long externalPartyDbId;

    public TransactionView(Long id, Date executionDate, String originAccountName, Long originAccountDbId, String destinationAccountName, Long destinationAccountDbId, BigDecimal amount, String description, String relatedPartyName, Long externalPartyDbId, AccountTypeEnum originAccountType, AccountTypeEnum destinationAccountType) {
        this.dbId = id;
        this.executionDate = executionDate;
        this.originAccountName = originAccountName;
        this.originAccountDbId = originAccountDbId;
        this.destinationAccountName = destinationAccountName;
        this.destinationAccountDbId = destinationAccountDbId;
        this.amount = amount;
        this.description = description;
        this.relatedPartyName = relatedPartyName;
        this.externalPartyDbId = externalPartyDbId;
        this.originAccountType = originAccountType;
        this.destinationAccountType = destinationAccountType;
    }

    public Long getDbId() {
        return dbId;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public String getOriginAccountName() {
        return originAccountName;
    }

    public Long getOriginAccountDbId() {
        return originAccountDbId;
    }

    public String getDestinationAccountName() {
        return destinationAccountName;
    }

    public Long getDestinationAccountDbId() {
        return destinationAccountDbId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getRelatedPartyName() {
        return relatedPartyName;
    }

    public Long getExternalPartyDbId() {
        return externalPartyDbId;
    }

    public AccountTypeEnum getOriginAccountType() {
        return originAccountType;
    }

    public AccountTypeEnum getDestinationAccountType() {
        return destinationAccountType;
    }

    @Override
    public String toString() {
        return "TransactionView{" +
                "dbId=" + dbId +
                ", executionDate=" + executionDate +
                ", originAccountName='" + originAccountName + '\'' +
                ", originAccountDbId=" + originAccountDbId +
                ", originAccountType=" + originAccountType +
                ", destinationAccountName='" + destinationAccountName + '\'' +
                ", destinationAccountDbId=" + destinationAccountDbId +
                ", destinationAccountType=" + destinationAccountType +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", relatedPartyName='" + relatedPartyName + '\'' +
                ", externalPartyDbId=" + externalPartyDbId +
                '}';
    }
}

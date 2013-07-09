package plb.accounting.model;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author pbala
 */
@MappedSuperclass
@Table(name = "ACCOUNTS", uniqueConstraints = {@UniqueConstraint(columnNames = {"ACC_NAME", "ACC_PARENT_ACCOUNT"})})
public abstract class AbstractAccount extends BaseEntity implements IAccount {
    /**
     *
     */
    @Column(name = "ACC_NAME", nullable = false)
    protected String name;
    /**
     *
     */
    @Column(name = "ACC_INITIAL_BALANCE", nullable = false)
    private BigDecimal initialBalance;
    /**
     *
     */
    @Column(name = "ACC_CURRENT_BALANCE", nullable = false)
    private BigDecimal currentBalance;
    /**
     *
     */
    @ManyToOne
    @JoinColumn(name = "ACC_PARENT_ACCOUNT")
    protected IAccount parentAccount;

    /**
     *
     */
    @Column(name = "ACC_DECRIPTION", length = 500)
    private String description;

    /**
     *
     */
    @Column(name = "ACC_TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountTypeEnum type;

    /**
     *
     */
    protected AbstractAccount() {
        //JPA
    }

    /**
     * @param name
     * @param type
     * @param initialBalance
     */
    public AbstractAccount(String name, AccountTypeEnum type, BigDecimal initialBalance) {
        setName(name);
        setType(type);
        setInitialBalance(initialBalance);
        setCurrentBalance(initialBalance);
    }


    /**
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    @Override
    public void setName(String name) {
        Assert.hasLength(name);
        this.name = name;
    }

    /**
     * @return
     */
    @Override
    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    /**
     * @param initialBalance
     */
    @Override
    public void setInitialBalance(BigDecimal initialBalance) {
        Assert.notNull(initialBalance);
        Assert.isTrue(BigDecimal.ZERO.compareTo(initialBalance) <= 0);
        this.initialBalance = initialBalance;
    }

    /**
     * @return
     */
    @Override
    public BigDecimal getCurrentBalance() {
        return currentBalance;
    }

    /**
     * @param currentBalance
     */
    protected void setCurrentBalance(BigDecimal currentBalance) {
        Assert.notNull(currentBalance);
        Assert.isTrue(BigDecimal.ZERO.compareTo(initialBalance) <= 0);
        this.currentBalance = currentBalance;
    }

    /**
     * @return
     */
    @Override
    public IAccount getParentAccount() {
        return parentAccount;
    }

    /**
     * @param parentAccount
     */
    @Override
    public void setParentAccount(IAccount parentAccount) {
        this.parentAccount = parentAccount;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public AccountTypeEnum getType() {
        return type;
    }

    @Override
    public void setType(AccountTypeEnum type) {
        Assert.notNull(type);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                "} " + super.toString();
    }
}

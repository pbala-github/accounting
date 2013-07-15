package plb.accounting.model;

import org.hibernate.ejb.QueryHints;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author pbala
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ATYPE", discriminatorType = DiscriminatorType.STRING)
@Table(name = "ACCOUNTS", uniqueConstraints = {@UniqueConstraint(columnNames = {"ACC_NAME", "ACC_PARENT_ACCOUNT"})})
@TableGenerator(name = "Acc_Ids_Gen", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 5)
public abstract class AbstractAccount extends BaseEntity implements IAccount {

    @Id
    @Column(name = "ACC_ID")
    @GeneratedValue(generator = "Acc_Ids_Gen")
    private Long id;

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
    protected AccountComposite parentAccount;

    /**
     *
     */
    @Column(name = "ACC_DESCRIPTION", length = 500)
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


    @Override
    public Long getId() {
        return id;
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
    public AccountComposite getParentAccount() {
        return parentAccount;
    }

    /**
     * @param parentAccount
     */
    @Override
    public void setParentAccount(AccountComposite parentAccount) {
        if (this.parentAccount != null) {
            this.parentAccount.removeChildrenAccount(this);
        }
        if (parentAccount != null) {
            parentAccount.addChildrenAccount(this);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractAccount)) return false;
        if (!super.equals(o)) return false;

        AbstractAccount that = (AbstractAccount) o;

        if (!name.equals(that.name)) return false;
        if (parentAccount != null ? !parentAccount.equals(that.parentAccount) : that.parentAccount != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (parentAccount != null ? parentAccount.hashCode() : 0);
        return result;
    }
}

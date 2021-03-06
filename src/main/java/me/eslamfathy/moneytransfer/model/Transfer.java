package me.eslamfathy.moneytransfer.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "Transfer")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Account.class, optional = false)
    private Account sourceAccount;

    @ManyToOne(targetEntity = Account.class, optional = false)
    private Account destinationAccount;

    @NotNull
    @Min(value = 0)
    @Column(name = "value", precision = 15, scale = 4, nullable = false)
    private BigDecimal value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public Account getDestinationAccount() {
        return destinationAccount;
    }

    public void setDestinationAccount(Account destinationAccount) {
        this.destinationAccount = destinationAccount;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Transfer() {
    }

    public Transfer(Account sourceAccount, Account destinationAccount, BigDecimal value) {
        this.sourceAccount = sourceAccount;
        this.destinationAccount = destinationAccount;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", sourceAccount=" + sourceAccount +
                ", destinationAccount=" + destinationAccount +
                ", value=" + value +
                '}';
    }
}

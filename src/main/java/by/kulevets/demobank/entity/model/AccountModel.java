package by.kulevets.demobank.entity.model;

import by.kulevets.demobank.entity.enumeration.AccountStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class AccountModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private AccountStatus status;

    @Column(name = "amount")
    private BigDecimal amount;

    public AccountModel(Long id, Long userId, AccountStatus status, BigDecimal amount) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.amount = amount;
    }

    public AccountModel() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountModel that = (AccountModel) o;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId) && status == that.status && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, status, amount);
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}

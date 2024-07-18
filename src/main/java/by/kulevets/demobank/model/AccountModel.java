package by.kulevets.demobank.model;

import by.kulevets.demobank.model.enumeration.AccountStatus;

import java.util.Objects;

public class AccountModel {
    private final Long id;
    private final Long userId;
    private final AccountStatus status;
    private final Double amount;

    public AccountModel(Long id, Long userId, AccountStatus status, Double amount) {
        this.id = id;
        this.userId = userId;
        this.status = status;
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

    public Double getAmount() {
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

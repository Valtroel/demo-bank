package by.kulevets.demobank.entity.model;

import by.kulevets.demobank.entity.enumeration.AccountStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "accounts")
public class AccountModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserInfo user;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private AccountStatus status;

    @Column(name = "amount")
    private BigDecimal amount;

    public AccountModel(Long id, UserModel user, AccountStatus status, BigDecimal amount) {
        this.id = id;
        this.user = new UserInfo(user.getId(), user.getUserName());
        this.status = status;
        this.amount = amount;
    }

    public AccountModel() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setuser(UserModel user) {
        this.user = new UserInfo(user.getId(), user.getUserName());
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

    public UserInfo getuser() {
        return user;
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
        return Objects.equals(id, that.id) && Objects.equals(user, that.user) && status == that.status && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, status, amount);
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "id=" + id +
                ", user=" + user.toString() +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }


    @Entity
    @Table(name = "users")
    public static class UserInfo {

        @Id
        @Column(name = "id")
        private Long userId;

        @Column(name = "user_name")
        private String userName;

        public UserInfo(Long userId, String userName) {
            this.userId = userId;
            this.userName = userName;
        }

        public UserInfo() {
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            UserInfo userInfo = (UserInfo) o;
            return Objects.equals(userId, userInfo.userId) && Objects.equals(userName, userInfo.userName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, userName);
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "userId=" + userId +
                    ", userName='" + userName + '\'' +
                    '}';
        }
    }
}

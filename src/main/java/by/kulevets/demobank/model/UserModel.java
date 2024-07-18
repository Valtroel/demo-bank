package by.kulevets.demobank.model;

import by.kulevets.demobank.model.enumeration.UserRole;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserModel  implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "role")
    private UserRole role;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    public UserModel(Long id, UserRole role, String userName, String password) {
        this.id = id;
        this.role = role;
        this.userName = userName;
        this.password = password;
    }

    public UserModel() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public UserRole getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && role == userModel.role && Objects.equals(userName, userModel.userName) && Objects.equals(password, userModel.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, userName, password);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", role=" + role +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

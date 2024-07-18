package by.kulevets.demobank.repository;

import by.kulevets.demobank.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, Long> {
}

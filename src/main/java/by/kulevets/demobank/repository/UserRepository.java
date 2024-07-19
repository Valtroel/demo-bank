package by.kulevets.demobank.repository;

import by.kulevets.demobank.entity.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByUserName(String userName);
    UserModel findByUserNameAndPassword(String userName, String password);

    Optional<UserModel> findById(Long id);
}

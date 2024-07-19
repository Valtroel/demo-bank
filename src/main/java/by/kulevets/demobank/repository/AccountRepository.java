package by.kulevets.demobank.repository;

import by.kulevets.demobank.entity.model.AccountModel;
import by.kulevets.demobank.entity.enumeration.AccountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {

    @Modifying
    @Transactional
    @Query(value = "update accounts a set a.status=:newStatus where a.id=:id", nativeQuery = true)
    AccountModel updateAccountStatus(Long id, AccountStatus newStatus);

    AccountModel findByUserId(Long userId);
}

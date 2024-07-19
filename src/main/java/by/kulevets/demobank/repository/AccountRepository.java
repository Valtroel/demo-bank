package by.kulevets.demobank.repository;

import by.kulevets.demobank.entity.model.AccountModel;
import by.kulevets.demobank.entity.enumeration.AccountStatus;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountModel, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.accounts SET status=:newStatus where id=:id", nativeQuery = true)
    void updateAccountStatus(@Param("id") Long id,@Param("newStatus") String newStatus);


    Optional<AccountModel> findById(@NonNull Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE public.accounts SET amount=:newAmount where id=:id", nativeQuery = true)
    int updateAmount(@Param("id") Long id,@Param("newAmount") BigDecimal newAmount);

}

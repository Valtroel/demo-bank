package by.kulevets.demobank.config;

import by.kulevets.demobank.entity.enumeration.AccountStatus;
import by.kulevets.demobank.entity.model.AccountModel;
import by.kulevets.demobank.entity.model.UserModel;
import by.kulevets.demobank.entity.enumeration.UserRole;
import by.kulevets.demobank.repository.AccountRepository;
import by.kulevets.demobank.repository.UserRepository;
import by.kulevets.demobank.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DataIntegrityViolationException;

import java.math.BigDecimal;

@Configuration
public class PrepareDbConfig {

    private static final Logger log = LoggerFactory.getLogger(PrepareDbConfig.class);


    @Bean
    CommandLineRunner initDbData(UserRepository userRepository, AccountRepository accountRepository) {
        return args -> {
            try {
                UserModel admin = userRepository.save(
                        new UserModel(
                                null,
                                UserRole.ADMIN,
                                "admin",
                                PasswordUtil.hashPassword("admin")
                        )
                );
                UserModel user1 = userRepository.save(
                        new UserModel(
                                null,
                                UserRole.USER,
                                "user1",
                                PasswordUtil.hashPassword("user1")
                        )
                );
                UserModel user2 = userRepository.save(
                        new UserModel(
                                null,
                                UserRole.USER,
                                "user2",
                                PasswordUtil.hashPassword("user2")
                        )
                );
                log.info("Preparing admin: " + admin);
                log.info("Preparing user1: " + user1);
                log.info("Preparing user2: " + user2);

                AccountModel user1Account = accountRepository
                        .save(
                                new AccountModel(
                                        null,
                                        user1.getId(),
                                        AccountStatus.ACTIVE,
                                        new BigDecimal(0)
                                )
                        );
                log.info("Preparing account for user1: " + user1Account);

                AccountModel user2Account = accountRepository
                        .save(
                                new AccountModel(
                                        null,
                                        user2.getId(),
                                        AccountStatus.ACTIVE,
                                        new BigDecimal(0)
                                )
                        );
                log.info("Preparing account for user2: " + user2Account);
            } catch (DataIntegrityViolationException e) {
                log.info("Users already exist");

                try {
                    UserModel user1 = userRepository.findByUserName("user1");

                    AccountModel user1Account = accountRepository
                            .save(
                                    new AccountModel(
                                            null,
                                            user1.getId(),
                                            AccountStatus.ACTIVE,
                                            new BigDecimal(0)
                                    )
                            );
                    log.info("Preparing account for user1: " + user1Account);

                    UserModel user2 = userRepository.findByUserName("user2");

                    AccountModel user2Account = accountRepository
                            .save(
                                    new AccountModel(
                                            null,
                                            user2.getId(),
                                            AccountStatus.ACTIVE,
                                            new BigDecimal(0)
                                    )
                            );
                    log.info("Preparing account for user2: " + user2Account);
                } catch (DataIntegrityViolationException ex) {
                    log.info("Accounts already exist");
                }

            }
        };
    }
}

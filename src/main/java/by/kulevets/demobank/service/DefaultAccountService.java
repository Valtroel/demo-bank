package by.kulevets.demobank.service;

import by.kulevets.demobank.entity.model.AccountModel;
import by.kulevets.demobank.repository.AccountRepository;
import org.hibernate.Cache;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Transactional
@Service
public class DefaultAccountService implements AccountService{

    private static final Logger log = LoggerFactory.getLogger(DefaultAccountService.class);

    private final AccountRepository accountRepository;

    public DefaultAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    @Override
    public AccountModel withdrawAccount(Long accountId, BigDecimal amount) {
        Optional<AccountModel> currentState = accountRepository.findById(accountId);

        if (currentState.isEmpty()){
            log.error("No account for id: "+ accountId);
            throw new IllegalStateException("No such account");
        }
        BigDecimal withdrawingResult = currentState.get().getAmount().subtract(amount);

        if (withdrawingResult.compareTo(new BigDecimal(0)) < 0){
            log.error("No enough money in account: "+ accountId);
            throw new IllegalArgumentException("No enough money in account");
        }

        int updateResult = accountRepository.updateAmount(accountId, withdrawingResult);

        if (updateResult < 0){
            throw new HibernateException("Something went wrong");
        }

        currentState.get().setAmount(withdrawingResult);
        return currentState.get();
    }

    @Transactional
    @Override
    public AccountModel depositAccount(Long accountId, BigDecimal amount) {
        Optional<AccountModel> currentState = accountRepository.findById(accountId);

        if (currentState.isEmpty()) {
            log.error("No account for id: " + accountId);
            throw new IllegalStateException("No such account");
        }
        BigDecimal depositResult = currentState.get().getAmount().add(amount);

        int updateResult = accountRepository.updateAmount(accountId, depositResult);

        if (updateResult < 0){
            throw new HibernateException("Something went wrong");
        }

        currentState.get().setAmount(depositResult);
        return currentState.get();
    }
}

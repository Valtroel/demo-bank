package by.kulevets.demobank.service;

import by.kulevets.demobank.entity.model.AccountModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AccountService {

    AccountModel withdrawAccount(final Long accountId, final BigDecimal amount);

    AccountModel depositAccount(final Long accountId, final BigDecimal amount);
}

package by.kulevets.demobank.controller;

import by.kulevets.demobank.entity.model.AccountModel;
import by.kulevets.demobank.entity.enumeration.AccountStatus;
import by.kulevets.demobank.repository.AccountRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AccountRepository repository;

    public AdminController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/accounts")
    @RolesAllowed({"ADMIN"})
    public List<AccountModel> accountsList(){
        return repository.findAll();
    }

    @PostMapping("/disable/{id}")
    public AccountModel disableAccount(@PathVariable String id){
        return repository.updateAccountStatus(Long.valueOf(id), AccountStatus.DISABLED);
    }

    @PostMapping("/activate/{id}")
    public AccountModel activateAccount(@PathVariable String id){
        return repository.updateAccountStatus(Long.valueOf(id), AccountStatus.ACTIVE);
    }
}

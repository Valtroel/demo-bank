package by.kulevets.demobank.controller;

import by.kulevets.demobank.entity.model.AccountModel;
import by.kulevets.demobank.entity.enumeration.AccountStatus;
import by.kulevets.demobank.repository.AccountRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(value = "https://localhost:3000/")
public class AdminController {

    private final AccountRepository repository;

    public AdminController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/accounts")
    public List<AccountModel> accountsList(){
        return repository.findAll();
    }

    @PostMapping("/disable/{id}")
    public AccountModel disableAccount(@PathVariable String id){
        repository.updateAccountStatus(Long.valueOf(id), AccountStatus.DISABLED.name());
        return repository.findById(Long.valueOf(id)).get();
    }

    @PostMapping("/activate/{id}")
    public AccountModel activateAccount(@PathVariable String id){
        repository.updateAccountStatus(Long.valueOf(id), AccountStatus.DISABLED.name());
        return repository.findById(Long.valueOf(id)).get();
    }
}

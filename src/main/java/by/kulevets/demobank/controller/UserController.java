package by.kulevets.demobank.controller;

import by.kulevets.demobank.entity.model.AccountModel;
import by.kulevets.demobank.entity.pojo.UserPojo;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping(
            value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @CrossOrigin(value = "https://localhost:3000/")
    public UserPojo login(@RequestBody UserPojo userPojo){
        return userPojo;
    }


    @GetMapping("/account")
    public AccountModel account(@RequestParam Long userId){
        return null;
    }

    @PostMapping("/withdraw")
    public AccountModel withdraw(
            @RequestParam final Long accountId,
            @RequestParam @NumberFormat(pattern = "#.###,#####") final BigDecimal amount
    ){
        return null;
    }

    @PostMapping("/deposit")
    public AccountModel deposit(
            @RequestParam final Long accountId,
            @RequestParam @NumberFormat(pattern = "#.###,#####") final BigDecimal amount
    ){
        return null;
    }
}

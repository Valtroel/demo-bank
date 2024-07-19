package by.kulevets.demobank.controller;

import by.kulevets.demobank.entity.model.AccountModel;
import by.kulevets.demobank.entity.pojo.UserPojo;
import by.kulevets.demobank.repository.AccountRepository;
import by.kulevets.demobank.repository.UserRepository;
import by.kulevets.demobank.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.logging.LogManager;


@RestController
@RequestMapping("/user")
@CrossOrigin(value = "https://localhost:3000/")
public class UserController {

    public static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    public UserController(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, AccountService accountService, UserRepository userRepository, AccountRepository accountRepository) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.accountService = accountService;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping(
            value = "/login",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<Object> login(@RequestBody UserPojo userPojo){
        Authentication authentication = authenticate(userPojo.getUserName(), userPojo.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Signed in user: " + authentication.getPrincipal().toString());
        return new ResponseEntity<>(authentication.getPrincipal(), HttpStatus.OK);
    }


    @GetMapping("/account")
    public AccountModel account(@RequestParam Long userId){
        return accountRepository.findByUser(new AccountModel.UserInfo(userId,userRepository.findById(userId).get().getUserName() )).get();
    }

    @PostMapping("/withdraw")
    public AccountModel withdraw(
            @RequestParam final Long accountId,
            @RequestParam @NumberFormat(pattern = "#.###,#####") final BigDecimal amount
    ){
        return accountService.withdrawAccount(accountId, amount);
    }

    @PostMapping("/deposit")
    public AccountModel deposit(
            @RequestParam final Long accountId,
            @RequestParam @NumberFormat(pattern = "#.###,#####") final BigDecimal amount
    ){
        return accountService.depositAccount(accountId, amount);
    }

    private Authentication authenticate(String username, String password) throws BadCredentialsException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if(userDetails == null) {
            log.info("Sign in details - null");
            log.error("Invalid username and password");
            throw new BadCredentialsException("Invalid username and password");
        }
        log.info("Sig in in user details"+ userDetails);
        if(!passwordEncoder.matches(password,userDetails.getPassword())) {
            log.info("Sign in userDetails - password mismatch"+userDetails);
            log.error("Invalid password");
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

    }
}

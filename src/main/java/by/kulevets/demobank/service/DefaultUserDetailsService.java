package by.kulevets.demobank.service;

import by.kulevets.demobank.entity.BankUserDetails;
import by.kulevets.demobank.entity.model.UserModel;
import by.kulevets.demobank.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public DefaultUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUserName(username);
        if (userModel == null){
            throw new UsernameNotFoundException(username + "not found");
        }
        return new BankUserDetails(userModel);
    }


}

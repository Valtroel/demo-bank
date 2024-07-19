package by.kulevets.demobank.service;

import by.kulevets.demobank.entity.model.UserModel;
import by.kulevets.demobank.entity.pojo.UserPojo;
import by.kulevets.demobank.repository.UserRepository;
import by.kulevets.demobank.util.PasswordUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultUserService  implements UserService, UserDetailsService {

    private final UserRepository userRepository;


    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username);
    }

    @Override
    public UserModel getUser(UserPojo pojo) {
        UserModel searchResult = userRepository.findByUserName(pojo.getUserName());
        if (searchResult != null){
            if (!PasswordUtil.checkPassword(pojo.getPassword(), searchResult.getPassword())){
                return null;
            }
        }
        return searchResult;
    }
}

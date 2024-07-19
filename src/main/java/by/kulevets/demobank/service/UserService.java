package by.kulevets.demobank.service;

import by.kulevets.demobank.entity.model.UserModel;
import by.kulevets.demobank.entity.pojo.UserPojo;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserModel getUser(UserPojo pojo);
}

package by.kulevets.demobank.mapper;


import by.kulevets.demobank.entity.model.UserModel;
import by.kulevets.demobank.entity.pojo.UserPojo;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserMapper implements UserMapper{

    @Override
    public UserModel toDto(UserPojo userPojo) {
        return null;
    }
}

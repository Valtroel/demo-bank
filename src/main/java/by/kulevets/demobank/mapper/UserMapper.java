package by.kulevets.demobank.mapper;

import by.kulevets.demobank.entity.model.UserModel;
import by.kulevets.demobank.entity.pojo.UserPojo;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {

    UserModel toDto(UserPojo userPojo);
}

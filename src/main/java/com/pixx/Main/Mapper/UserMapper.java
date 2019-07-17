package com.pixx.Main.Mapper;

import com.pixx.Main.Domain.UserDomain;
import com.pixx.Main.Model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends AbstractModelMapper<User, UserDomain>{
    @Override
    public Class<User> entityType() {
        return User.class;
    }

    @Override
    public Class<UserDomain> modelType() {
        return UserDomain.class;
    }
}

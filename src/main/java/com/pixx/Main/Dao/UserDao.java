package com.pixx.Main.Dao;

import com.pixx.Main.Domain.UserDomain;
import com.pixx.Main.Model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    public User addRes(UserDomain userDomain);
    public List<User> findByEmail(String email);
    public List<User> findByResetToken(String reset_token);
    public void save(User user);

}

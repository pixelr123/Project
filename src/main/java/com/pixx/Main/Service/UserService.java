package com.pixx.Main.Service;

import com.pixx.Main.Model.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User addReg(User user);
    public List<User> findUserByEmail(String email);
    public List<User> findUserByResetToken(String resetToken);
    public void save(User user);
}

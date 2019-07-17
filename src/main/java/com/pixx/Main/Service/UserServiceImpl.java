package com.pixx.Main.Service;

import com.pixx.Main.Dao.UserDao;
import com.pixx.Main.Domain.UserDomain;
//import com.pixx.Main.Mapper.UserMapper;
import com.pixx.Main.Model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public User addReg(User user){
        ModelMapper mapper = new ModelMapper();
        UserDomain userDomain = new UserDomain();
        mapper.map(user,userDomain);
        //userDomain.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.addRes(userDomain);
    }

    @Override
    public List<User> findUserByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public List<User> findUserByResetToken(String resetToken) {

        return userDao.findByResetToken(resetToken);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }
}

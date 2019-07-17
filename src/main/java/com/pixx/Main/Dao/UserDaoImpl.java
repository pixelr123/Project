package com.pixx.Main.Dao;

import com.pixx.Main.Domain.UserDomain;
import com.pixx.Main.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User addRes(UserDomain userDomain) {
        String sql = "insert into user(firstname,lastname,email,password) values (?,?,?,?)";
        jdbcTemplate.update(sql, new Object[] {userDomain.getFirstname(),userDomain.getLastname(),userDomain.getEmail(),userDomain.getPassword()});
        User user = new User();
        return user;
    }

    @Override
    public List<User> findByResetToken(String reset_token) {
        String sql = "SELECT * FROM user WHERE reset_token = ?";
        User user = new User();
        return jdbcTemplate.query(sql,new Object[]{user.getResetToken()},new BeanPropertyRowMapper<>());
    }

    @Override
    public List<User> findByEmail(String email) {
        String sql = "SELECT * FROM user where email = ?";
        User user = new User();
        return jdbcTemplate.query(sql,new Object[]{user.getEmail()},new BeanPropertyRowMapper<>());
    }

    @Override
    public void save(User user) {
        String sql = "Update user set password = '"+user.getPassword()+"' ";
        jdbcTemplate.update(sql);
    }
}

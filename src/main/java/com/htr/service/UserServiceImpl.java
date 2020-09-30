package com.htr.service;

import com.htr.dao.UserDao;
import com.htr.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: T. He
 * @Date: 2020/9/29
 */

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        User user = userDao.queryByUsernameAndPassword(username, password);
        return user;
    }
}

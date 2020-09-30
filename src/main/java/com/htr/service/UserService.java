package com.htr.service;

import com.htr.pojo.User;

/**
 * @Author: T. He
 * @Date: 2020/9/29
 */
public interface UserService {

    User checkUser(String username, String password);
}

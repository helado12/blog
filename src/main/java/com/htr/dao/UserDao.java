package com.htr.dao;

import com.htr.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: T. He
 * @Date: 2020/9/29
 */

@Repository
public class UserDao {
    public User queryByUsernameAndPassword(@Param("username") String username,@Param("password") String password){
        return null;
    }

}

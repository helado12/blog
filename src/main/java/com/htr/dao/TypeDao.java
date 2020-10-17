package com.htr.dao;

import com.htr.pojo.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/1
 */

@Repository
public interface TypeDao {

    int saveType(Type type);


    Type getType(Long id);


    List<Type> listType();


    int updateType(Type type);

    Type getTypeByName(String name);

    void deleteType(long id);
}

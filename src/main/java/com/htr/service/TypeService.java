package com.htr.service;

import com.htr.pojo.Type;

import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/1
 */
public interface TypeService {
    int saveType(Type type);

    Type getType(Long id);

    List<Type> listType();

    Type getTypeByName(String name);

    int updateType(Type type);

    void  deleteType(Long id);
}

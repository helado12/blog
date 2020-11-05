package com.htr.service;

import com.htr.dao.TypeDao;
import com.htr.pojo.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/1
 */

@Service
public class TypeServiceImpl implements TypeService{
    @Autowired
    TypeDao typeDao;

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);

    }

    @Transactional
    @Override
    public Type getType(Long id) {
        return typeDao.getType(id);
    }

    @Transactional
    @Override
    public List<Type> listType() {
        return typeDao.listType();
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    @Override
    public List<Type> getBlogType() {
        List<Type> types = typeDao.getBlogType();

        Collections.sort(types, new Comparator<Type>() {
            @Override
            public int compare(Type o1, Type o2) {
                Integer s1 = o1.getBlogs().size();
                Integer s2 = o2.getBlogs().size();
                return -s1.compareTo(s2);
            }
        });
        return types;
    }
}

package com.htr.service;

import com.htr.dao.TagDao;
import com.htr.pojo.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/1
 */

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    TagDao tagDao;

    @Transactional
    @Override
    public int saveTag(Tag tag) {
        return tagDao.saveTag(tag);

    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagDao.getTag(id);
    }

    @Transactional
    @Override
    public List<Tag> listTag() {
        return tagDao.listTag();
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Transactional
    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.deleteTag(id);
    }
}
package com.htr.service;

import com.htr.pojo.Tag;

import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/2
 */
public interface TagService {
    int saveTag(Tag tag);

    Tag getTag(Long id);

    List<Tag> listTag();

    Tag getTagByName(String name);

    int updateTag(Tag tag);

    void  deleteTag(Long id);
}

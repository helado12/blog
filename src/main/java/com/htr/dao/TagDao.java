package com.htr.dao;

import com.htr.pojo.Tag;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/2
 */

@Repository
public interface TagDao {
    int saveTag(Tag tag);


    Tag getTag(Long id);


    List<Tag> listTag();


    int updateTag(Tag tag);

    Tag getTagByName(String name);

    void deleteTag(Long id);
}

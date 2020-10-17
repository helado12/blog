package com.htr.dao;

import com.htr.pojo.Blog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/4
 */

@Repository
public interface BlogDao {


    public Blog getBlog(long id);

    public int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    void deleteBlog(Long id);

    List<Blog> listBlog(Blog blog);

    List<Blog> listAllBlog();
}

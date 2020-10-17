package com.htr.service;

import com.htr.pojo.Blog;

import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/4
 */
public interface BlogService {
    Blog getBlog(long id);

    List<Blog> listBlog(Blog blog);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    void deleteBlog(Long id);


    List<Blog> listAllBlog();
}

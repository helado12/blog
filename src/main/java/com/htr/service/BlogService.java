package com.htr.service;

import com.htr.pojo.Blog;
import com.htr.pojo.BlogAndTag;

import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/4
 */
public interface BlogService {
    Blog getBlog(long id);

    //convert markdown in blog content to html
    Blog getAndConvert(Long id);

    List<Blog> listBlog(Blog blog);

    int saveBlog(Blog blog);

    int updateBlog(Blog blog);

    void deleteBlog(Long id);


    List<Blog> listAllBlog();

    List<Blog> getIndexBlog();

    List<Blog> getBlogSearch(String query);

    List<Blog> getAllRecommendBlog();

    List<Blog> getByTypeId(Long id);

    List<Blog> getByTagId(Long id);
}

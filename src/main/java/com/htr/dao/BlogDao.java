package com.htr.dao;

import com.htr.pojo.Blog;
import com.htr.pojo.BlogAndTag;
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

    int deleteTagByBlog(Long id);

    void deleteBlog(Long id);

    List<Blog> listBlog(Blog blog);

    List<Blog> listAllBlog();

    int saveBlogTag(BlogAndTag blogAndTag);

    List<Blog> getIndexBlog();

    List<Blog> getAllRecommendBlog();

    List<Blog> getBlogSearch(String query);

    int updateViews(Long id);

    List<Blog> getByTypeId(Long id);

    List<Blog> getByTagId(Long id);

    List<String> queryBlogYear();

    List<Blog> findByYear(String year);
}

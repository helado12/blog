package com.htr.service;

import com.htr.NotFoundException;
import com.htr.dao.BlogDao;
import com.htr.pojo.Blog;
import com.htr.pojo.BlogAndTag;
import com.htr.pojo.Tag;
import com.htr.util.MarkdownUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/4
 */
@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogDao blogDao;
    @Autowired
    private TagService tagService;

    @Override
    public Blog getBlog(long id) {
        return blogDao.getBlog(id);
    }

    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogDao.getBlog(id);
        if (blog == null){
            throw new NotFoundException("Blog does not exist");
        }
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        String content = b.getContent();
        content = MarkdownUtils.markdownToHtmlExtensions(content);
        b.setContent(content);
        return b;
    }

    @Override
    public List<Blog> listBlog(Blog blog) {
        return blogDao.listBlog(blog);
    }

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else {
            blog.setUpdateTime(new Date());
        }
        for(Tag tag: tagService.listTag(blog.getTagIds())){
            blogDao.saveBlogTag(new BlogAndTag(blog.getId(),tag.getId()));
        }
        return blogDao.saveBlog(blog);
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        blog.setUpdateTime(new Date());
        return blogDao.updateBlog(blog);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Override
    public List<Blog> listAllBlog() {
       return blogDao.listAllBlog();
    }

    @Override
    public List<Blog> getIndexBlog() {
        return blogDao.getIndexBlog();
    }

    @Override
    public List<Blog> getBlogSearch(String query) {
        return blogDao.getBlogSearch(query);
    }

    @Override
    public List<Blog> getAllRecommendBlog() {
        return blogDao.getAllRecommendBlog();
    }

}


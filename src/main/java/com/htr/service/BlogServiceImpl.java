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

import java.util.*;

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
    @Transactional
    public Blog getAndConvert(Long id) {
        blogDao.updateViews(id);
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
            blogDao.saveBlogTag(new BlogAndTag(tag.getId(), blog.getId()));
        }
        return blogDao.saveBlog(blog);
    }

    @Transactional
    @Override
    public int updateBlog(Blog blog) {
        blogDao.deleteTagByBlog(blog.getId());
        for(Tag tag: tagService.listTag(blog.getTagIds())){
                blogDao.saveBlogTag(new BlogAndTag(tag.getId(), blog.getId()));
        }
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

    @Override
    public List<Blog> getByTypeId(Long id) {
        return blogDao.getByTypeId(id);
    }

    @Override
    public List<Blog> getByTagId(Long id) {
        return blogDao.getByTagId(id);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        Set<String> set = new HashSet<>(blogDao.queryBlogYear());
        List<String> blogYears = new ArrayList<>(set);
        Collections.sort(blogYears, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return - o1.compareTo(o2);
            }
        });
        Map<String, List<Blog>> map = new TreeMap<>(new Comparator<String>() {
            public int compare(String obj1, String obj2) {
                // 降序排序
                return obj2.compareTo(obj1);}
        });

        for (String year: blogYears){
            map.put(year, blogDao.findByYear(year));
        }

        return map;
    }

}


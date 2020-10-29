package com.htr.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htr.pojo.Blog;
import com.htr.pojo.Tag;
import com.htr.service.BlogService;
import com.htr.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/28
 */

@Controller
public class TagShowController {

    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id, @RequestParam(required = false,defaultValue = "1",value = "pagenum") int pagenum,
                        Model model){
        PageHelper.startPage(pagenum, 100);  //开启分页
        List<Tag> tags = tagService.getBlogTag();
        if (id == -1){
            id = tags.get(0).getId();
        }
        List<Blog> blogs = new ArrayList<>();
        // use getBlog() so that blog has all its tags
        for(Blog blog: blogService.getByTagId(id)){
            blogs.add(blogService.getBlog(blog.getId()));
        }
        PageInfo<Blog> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", tags);
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}

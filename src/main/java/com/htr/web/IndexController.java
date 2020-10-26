package com.htr.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htr.NotFoundException;
import com.htr.pojo.Blog;
import com.htr.pojo.Tag;
import com.htr.pojo.Type;
import com.htr.service.BlogService;
import com.htr.service.TagService;
import com.htr.service.TypeService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;
//    @GetMapping("/{id}/{name}")
//    public String index(@PathVariable Integer id, @PathVariable String name){
//
//        return "index";
//    }

    @GetMapping("/")
    public String toIndex(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum, Model model){
        PageHelper.startPage(pagenum, 8);
        List<Blog> allBlog = blogService.getIndexBlog();
        List<Type>  allType = typeService.getBlogType();
        List<Tag> allTag = tagService.getBlogTag();
        List<Blog> recommendBlog = blogService.getAllRecommendBlog();

        PageInfo pageInfo = new PageInfo(allBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("tags", allTag);
        model.addAttribute("types", allType);
        model.addAttribute("recommendBlogs", recommendBlog);
        return "index";
    }

    @PostMapping("/search")
    public String search(@RequestParam(required = false, defaultValue = "1", value = "pagenum") int pagenum,
                         @RequestParam String query, Model model){
        PageHelper.startPage(pagenum,8);
        List<Blog> allBlogSearch = blogService.getBlogSearch(query);

        PageInfo pageInfo = new PageInfo(allBlogSearch);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blog(@PathVariable Long id, Model model){
        model.addAttribute("blog", blogService.getBlog(id));
        return "blog";
    }

}

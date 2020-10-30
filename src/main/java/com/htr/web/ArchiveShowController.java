package com.htr.web;

import com.htr.pojo.Blog;
import com.htr.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/29
 */

@Controller
public class ArchiveShowController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/archives")
    public String archive(Model model){
        List<Blog> blogs = blogService.listAllBlog();
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount", blogs.size());
        return "archives";
    }
}

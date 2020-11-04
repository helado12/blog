package com.htr.web.admin;

import com.htr.pojo.Blog;
import com.htr.pojo.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @Author: T. He
 * @Date: 2020/9/30
 */

@Controller
@RequestMapping("/admin")
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @GetMapping("/blogs")
    public String blogs(Model model, HttpSession session){
        model.addAttribute("blogs", blogService.listAllBlog());
        model.addAttribute("types", typeService.listType());
        return "admin/blogs";
    }


    @PostMapping("/blogs/search")
    public String search(Blog blog, Model model){
        model.addAttribute("blogs", blogService.listBlog(blog));
        return "admin/blogs::blogList";
    }

    @GetMapping("/blogs/input")
    public String input(Model model){
        model.addAttribute("blog", new Blog());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("types", typeService.listType());
        return "admin/blogs-input";
    }

    public void setTypeAndTag(Model model){
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
    }

    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        if(blog != null) blog.init();
        model.addAttribute("blog", blog);
        return  "admin/blogs-input";
    }

    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setUserId(blog.getUser().getId());
        blog.setType(typeService.getType(blog.getType().getId()));
        blog.setTypeId(blog.getType().getId());
        blog.setTags(tagService.listTag(blog.getTagIds()));
        if (blog.getId() == null) {   //id为空，则为新增
            blogService.saveBlog(blog);
        } else {
            blogService.updateBlog(blog);
        }
        attributes.addFlashAttribute("message", "Congrats! Add successfully");
        return "redirect:/admin/blogs";
    }

    @RequestMapping("/blogs/{id}/delete")
    public String deleteBlog(@PathVariable long id){
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }
}

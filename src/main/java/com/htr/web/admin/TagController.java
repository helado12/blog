package com.htr.web.admin;

import com.htr.pojo.Tag;
import com.htr.pojo.Type;
import com.htr.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Author: T. He
 * @Date: 2020/10/2
 */
@Controller
@RequestMapping("/admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public String tags(Model model){
        model.addAttribute("tags", tagService.listTag());
        return "/admin/tags";
    }

    @GetMapping("/tags/input")
    public String input(Model model){
        model.addAttribute(new Tag());
        return "admin/tags-input";
    }

    @PostMapping("/tags")
    public String tagInput(Tag tag, Model model, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null){
            model.addAttribute("msg", "Category name already exists");
            return "admin/tags-input";
        }

        if(tag.getName() == "") {
            model.addAttribute("msg", "Category name cannot be empty");
            return "admin/tags-input";
        }

        tagService.saveTag(tag);
        attributes.addFlashAttribute("message", "Congrats! Add successfully");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/input")
    public String editTag(@PathVariable long id, Model model){
        model.addAttribute(tagService.getTag(id));
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String editPost(Tag tag, Model model, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByName(tag.getName());
        if (tag1 != null){
            model.addAttribute("msg", "Category name already exists");
            return "admin/tags-input";
        }

        if(tag.getName() == "") {
            model.addAttribute("msg", "Category name cannot be empty");
            return "admin/tags-input";
        }

        tagService.updateTag(tag);
        attributes.addFlashAttribute("message", "Congrats! Add successfully");
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable long id, RedirectAttributes attributes){
        tagService.deleteTag(id);
        attributes.addFlashAttribute("meesage", "Delete successfully");
        return "redirect:/admin/tags";
    }

}

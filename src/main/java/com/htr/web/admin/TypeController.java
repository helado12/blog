package com.htr.web.admin;

import com.htr.pojo.Type;
import com.htr.service.TypeServiceImpl;
import org.apache.ibatis.annotations.Param;
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
 * @Date: 2020/10/1
 */
@Controller
@RequestMapping("/admin")
public class TypeController {

    @Autowired
    TypeServiceImpl typeService;

    @GetMapping("/types")
    public String types(Model model){
        model.addAttribute("types", typeService.listType());
        return "admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model){
        model.addAttribute(new Type());
        return "admin/types-input";
    }

    @PostMapping("/types")
    public String post(Type type, Model model, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            model.addAttribute("msg", "Category name already exists");
            return "admin/types-input";
        }

        if(type.getName() == "") {
            model.addAttribute("msg", "Category name cannot be empty");
            return "admin/types-input";
        }

        typeService.saveType(type);
        attributes.addFlashAttribute("message", "Congrats! Add successfully");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    @PostMapping("/types/{id}")
    public String editPost(Type type, Model model, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if (type1 != null){
            model.addAttribute("msg", "Category name already exists");
            return "admin/types-input";
        }

        if(type.getName() == "") {
            model.addAttribute("msg", "Category name cannot be empty");
            return "admin/types-input";
        }

        typeService.updateType(type);
        attributes.addFlashAttribute("message", "Congrats! Add successfully");
        return "redirect:/admin/types";
    }

    @GetMapping("/types/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes){
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "Congrats! Delete successfully");
        return "redirect:/admin/types";
    }



}

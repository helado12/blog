package com.htr.web;

import com.htr.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/{id}/{name}")
    public String index(@PathVariable Integer id, @PathVariable String name){
//        int i = 9/0;
//        String blog = null;
//        if (blog == null){
//            throw new NotFoundException("Blog does not exist");
//        }

        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
        return "blog";
    }
}

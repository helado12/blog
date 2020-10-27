package com.htr.web;

import com.htr.pojo.Comment;
import com.htr.service.BlogService;
import com.htr.service.BlogServiceImpl;
import com.htr.service.CommentService;
import com.htr.service.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @Author: T. He
 * @Date: 2020/10/27
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable Long blogId, Model model){
        model.addAttribute("comments", commentService.findByBlogIdAndParentCommentNull(blogId));
        return "blog :: commentList";
    }

    @PostMapping("/comments")
    public String post(Comment comment){
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        comment.setBlogId(blogId);
        comment.setAvatar(avatar);
        commentService.saveComment(comment);
        return "redirect:/comments/" + comment.getBlog().getId();
    }
}

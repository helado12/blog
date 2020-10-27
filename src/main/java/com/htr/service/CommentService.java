package com.htr.service;

import com.htr.pojo.Comment;

import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/27
 */
public interface CommentService {

    List<Comment> findByBlogIdAndParentCommentNull(long id);

    int saveComment(Comment comment);


}

package com.htr.dao;

import com.htr.pojo.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/27
 */

@Repository
public interface CommentDao {


    List<Comment> findByBlogIdAndParentCommentNull(long id);

    List<Comment> getReplyComment(Long parentCommentId);

    int saveComment(Comment comment);
}

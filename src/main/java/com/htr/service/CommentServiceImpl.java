package com.htr.service;

import com.htr.dao.CommentDao;
import com.htr.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/10/27
 */

@Service
public class CommentServiceImpl implements CommentService{


    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> findByBlogIdAndParentCommentNull(long id) {
        return commentDao.findByBlogIdAndParentCommentNull(id);
    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1){
            comment.setParentComment(commentDao.searchComment(parentCommentId));
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        comment.setParentCommentId(parentCommentId);
        return commentDao.saveComment(comment);
    }
}

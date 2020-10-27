package com.htr.service;

import com.htr.dao.CommentDao;
import com.htr.pojo.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        List<Comment> rootComments = commentDao.findByBlogIdAndParentCommentNull(id);
        return rootCommentsWithReplyComments(rootComments);

    }

    @Transactional
    @Override
    public int saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        comment.setParentCommentId(parentCommentId);
        comment.setCreateTime(new Date());
        return commentDao.saveComment(comment);
    }


    //get reply comments for a root comment
    private void addReplyComments(Comment c, List reply){
        List<Comment> replyToComment = commentDao.getReplyComment(c.getId());
        for (Comment comment: replyToComment){
            reply.add(comment);
            addReplyComments(comment, reply);
        }
    }

    //copy and set reply comments for all root comments
    private List<Comment> rootCommentsWithReplyComments(List<Comment> comments){
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment: comments){
            Comment comment1 = new Comment();
            BeanUtils.copyProperties(comment, comment1);
            List<Comment> replyComments = new ArrayList<>();
            addReplyComments(comment1, replyComments);
            comment1.setReplyComments(replyComments);
            commentsView.add(comment1);
        }
        return commentsView;
    }
}

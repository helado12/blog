package com.htr.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/9/26
 */

@Data
@Alias("Comment")
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private boolean adminComment;  //是否为管理员评论

    private Long blogId;
    private Long parentCommentId;
    private String parentNickname;

    private Date createTime;

//    多对一
    private Blog blog;

//    一对多
    private List<Comment> replyComments = new ArrayList<>();

//    多对一
    private Comment parentComment;
}

package com.htr.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/9/26
 */

@Data
public class Comment {

    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;

    private Date createTime;

//    多对一
    private Blog blog;

//    一对多
    private List<Comment> replyComments = new ArrayList<>();

//    多对一
    private Comment parentComment;
}

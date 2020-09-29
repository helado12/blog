package com.htr.pojo;

import lombok.Data;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/9/26
 */

@Data
public class Blog {
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;

    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentabled;
    private boolean published;
    private boolean recommend;

    private Date creatTime;
    private Date updateTime;
//    多对一
    private User user;
//    多对一
    private Type type;

//    多对多
    private List<Tag> tags = new ArrayList<>();

//    一对多
    private List<Comment> comments = new ArrayList<>();


    public Blog() {



    }



}

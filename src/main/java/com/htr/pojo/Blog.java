package com.htr.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/9/26
 */

@Data
@Alias("Blog")
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

    private Date createTime;
    private Date updateTime;

    private String tagIds;
    private String description;

    private long userId;
    private long typeId;

//    多对一
    private User user;
//    多对一
    private Type type;

//    多对多
    private List<Tag> tags = new ArrayList<>();

//    一对多
    private List<Comment> comments = new ArrayList<>();

    public void init(){
        this.tagIds = tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags) {
        if(!tags.isEmpty()){
            StringBuilder ids = new StringBuilder();
            boolean flag = false;
            for (Tag tag: tags){
                if (flag){
                    ids.append(",");
                }else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        }else {
            return tagIds;
        }
    }

    public Blog() {



    }



}

package com.htr.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: T. He
 * @Date: 2020/9/26
 */
@Data
@Alias("Tag")
public class Tag {

    private Long id;
    private String name;

//    多对多
    private List<Blog> blogs = new ArrayList<>();
}

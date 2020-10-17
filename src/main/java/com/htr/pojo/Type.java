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
@Alias("Type")
public class Type {

    private Long id;
    private String name;

//  一对多
    private List<Blog> blogs = new ArrayList<>();
}

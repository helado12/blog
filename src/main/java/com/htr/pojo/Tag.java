package com.htr.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author: T. He
 * @Date: 2020/9/26
 */
@Data
@Alias("Tag")
public class Tag {

    private Long id;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //    多对多
    private List<Blog> blogs = new ArrayList<>();
}

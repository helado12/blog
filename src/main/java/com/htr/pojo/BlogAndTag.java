package com.htr.pojo;

import lombok.Data;

/**
 * @Author: T. He
 * @Date: 2020/9/29
 */

@Data
public class BlogAndTag {

    private long tagId;
    private long blogId;

    public BlogAndTag(long tagId, long blogId) {
        this.tagId = tagId;
        this.blogId = blogId;
    }
}

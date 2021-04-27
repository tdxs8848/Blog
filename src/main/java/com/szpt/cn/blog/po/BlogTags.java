package com.szpt.cn.blog.po;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "t_blog_tags")
public class BlogTags implements Serializable {
    @Id
    @GeneratedValue
    private Long blogs_id;
    private Long tags_id;


    public Long getBlogs_id() {
        return blogs_id;
    }

    public void setBlogs_id(Long blogs_id) {
        this.blogs_id = blogs_id;
    }

    public Long getTags_id() {
        return tags_id;
    }

    public void setTags_id(Long tags_id) {
        this.tags_id = tags_id;
    }

    public BlogTags() {
    }

}

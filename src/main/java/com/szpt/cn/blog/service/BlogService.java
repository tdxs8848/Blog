package com.szpt.cn.blog.service;

import com.szpt.cn.blog.po.Blog;
import com.szpt.cn.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlogIndex(Pageable pageable);

    Page<Blog> listBlog(String query,Pageable pageable);

    Page<Blog> listBlog(Pageable pageable,Blog blog);

    Page<Blog> listBlog(Long tagId,Pageable pageable);


    Page<Blog> listBlog(Pageable pageable, BlogQuery blogQuery);


//    Page<Blog> listBlog(BlogQuery ,Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer size);

    Blog saveBlog(Blog blog);

    Blog updataBlog(Long id,Blog blog);

    void deleteBlog(Long id);

}

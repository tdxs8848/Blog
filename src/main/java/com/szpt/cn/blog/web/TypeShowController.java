package com.szpt.cn.blog.web;

import com.szpt.cn.blog.po.Type;
import com.szpt.cn.blog.service.BlogService;
import com.szpt.cn.blog.service.TypeService;
import com.szpt.cn.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private BlogService blogService;


    @GetMapping("types/{id}")
    public String types(@PageableDefault(size = 8,sort = {"updataTime"},
            direction = Sort.Direction.DESC) Pageable p, @PathVariable Long id, Model model){

        List<Type> types = typeService.listTypeTop(1000);
        if(id == -1){
            id = types.get(0).getId();
        }

        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);

        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(p,blogQuery));
        model.addAttribute("activeTypeId",id);
        //页脚最新博客
        model.addAttribute("recommendBlogs3",blogService.listRecommendBlogTop(3));
        return "types";



    }

}

package com.szpt.cn.blog.web;



import com.szpt.cn.blog.po.Blog;
import com.szpt.cn.blog.service.BlogService;
import com.szpt.cn.blog.service.TagService;
import com.szpt.cn.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class indexController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;

    @GetMapping("/")
    public String index(@PageableDefault(size = 8,sort = {"updataTime"},
            direction = Sort.Direction.DESC) Pageable p, Model model){
        model.addAttribute("page",blogService.listBlogIndex(p));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs",blogService.listRecommendBlogTop(8));
        //页脚最新博客
        model.addAttribute("recommendBlogs3",blogService.listRecommendBlogTop(3));
        return "index";
    }

    @GetMapping("/blog")
    public String bbblog(){
        return "blog";
    }



    @PostMapping("/search")
    public String search(@PageableDefault(size = 8,sort = {"updataTime"},
            direction = Sort.Direction.DESC) Pageable p, @RequestParam String query ,
                         Model model){

        model.addAttribute("page",blogService.listBlog("%"+query+"%",p));
        model.addAttribute("query",query);
        //页脚最新博客
        model.addAttribute("recommendBlogs3",blogService.listRecommendBlogTop(3));
        return "search";
    }

    @GetMapping("blog/{id}")
    public String blog(@PathVariable Long id,Model model){
        model.addAttribute("blog",blogService.getAndConvert(id));
        //页脚最新博客
        model.addAttribute("recommendBlogs3",blogService.listRecommendBlogTop(3));
        //浏览记录加一
        Blog blog = blogService.getBlog(id);
        blog.setViews(blog.getViews()+1);
        blogService.saveBlog(blog);

        return "blog";
    }

    @GetMapping("/about")
    public String about(Model model){
        //页脚最新博客
        model.addAttribute("recommendBlogs3",blogService.listRecommendBlogTop(3));
        return "about";
    }

    @GetMapping("/tags")
    public String tags(Model model)
    {
        //页脚最新博客
        model.addAttribute("recommendBlogs3",blogService.listRecommendBlogTop(3));
        return "tags";
    }

    @GetMapping("/types")
    public String types(){
        return "types";
    }



}

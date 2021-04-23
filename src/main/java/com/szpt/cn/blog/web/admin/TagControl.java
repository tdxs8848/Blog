package com.szpt.cn.blog.web.admin;

import com.szpt.cn.blog.po.Tag;
import com.szpt.cn.blog.po.Type;
import com.szpt.cn.blog.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TagControl {

    @Autowired
    private TagService tagService;

    @GetMapping("/tag")
    public String Tag(@PageableDefault(size = 4,sort = {"id"},
            direction = Sort.Direction.DESC) Pageable p, Model model){
        model.addAttribute("page",tagService.listTag(p));

        return "admin/tag";
    }


    @GetMapping("/tag/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.listTag());
        model.addAttribute("tag",tagService.getTag(id));
        return "admin/tag-input";
    }



    @GetMapping("/tag/input")
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/tag-input";
    }
    @PostMapping("/tag")
    public String post(Tag tag , BindingResult result, RedirectAttributes attributes){
        Tag tag1 = tagService.getTagByname(tag.getName());
        if(tag1!=null){
            result.rejectValue("name","nameError","不能重复添加标签");
        }
        Tag t = tagService.saveTag(tag);
        if( t == null){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/tag";
    }
@PostMapping("/tag/{id}")
    public String editpost(Tag tag, @PathVariable Long id, RedirectAttributes attributes){

        Tag t = tagService.updataTag(id,tag);
        if( t == null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/tag";
    }
@GetMapping("/tag/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        tagService.deleteTag(id);
    attributes.addFlashAttribute("message","删除成功");
    return "redirect:/admin/tag";
    }


}

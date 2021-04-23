package com.szpt.cn.blog.web.admin;

import com.szpt.cn.blog.po.Type;
import com.szpt.cn.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeControl {

    @Autowired
    private TypeService typeService;

    @GetMapping("/type")
    public String type(@PageableDefault(size = 4,sort = {"id"},
            direction = Sort.Direction.DESC) Pageable p, Model model){
        model.addAttribute("page",typeService.listType(p));

        return "admin/type";
    }


    @GetMapping("type/{id}/input")
    public String editInput(@PathVariable Long id, Model model){
        model.addAttribute("type",typeService.listType());
        model.addAttribute("type",typeService.getType(id));
        return "admin/type-input";
    }



    @GetMapping("/type/input")
    public String input(Model model){
        model.addAttribute("type",new Type());
        return "admin/type-input";
    }
    @PostMapping("/type")
    public String post(Type type , BindingResult result, RedirectAttributes attributes){
        Type type1 = typeService.getTypeByName(type.getName());
        if(type1!=null){
            result.rejectValue("name","nameError","不能重复添加分类");
        }
        Type t = typeService.saveType(type);
        if( t == null){
            attributes.addFlashAttribute("message","新增失败");
        }else{
            attributes.addFlashAttribute("message","新增成功");
        }
        return "redirect:/admin/type";
    }
@PostMapping("/type/{id}")
    public String editpost(Type type, @PathVariable Long id, RedirectAttributes attributes){

        Type t = typeService.updateType(id,type);
        if( t == null){
            attributes.addFlashAttribute("message","更新失败");
        }else{
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/type";
    }
@GetMapping("/type/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes){
        typeService.deleteType(id);
    attributes.addFlashAttribute("message","删除成功");
    return "redirect:/admin/type";
    }


}

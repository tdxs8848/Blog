package com.szpt.cn.blog.web.admin;
import com.szpt.cn.blog.po.User;
import com.szpt.cn.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String loginPage(HttpServletRequest request,
                            HttpServletResponse response) throws IOException {
        //成功登录，重定向到后台首页页面
        if(request.getSession().getAttribute("user") != null){
            response.sendRedirect("admin/index");
        }
        return "admin/login";
    }

    //判断登录密码是否正确
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password ,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if(user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/index";
        }else {
            attributes.addFlashAttribute("message","用户名和密码错误");

            return "redirect:/admin";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("user");
        return "redirect:/admin";
    }
}

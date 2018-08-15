package com.yt.logging.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Hello {

    @RequestMapping("/hello")
    public String hello(Model model) {

        model.addAttribute("name", "yt");

        return "hello";
    }

    @RequestMapping("/tologin")
    public String tologin() {

        return "/login";
    }

    @RequestMapping("/add")
    public String add() {
        return "/user/add";
    }

    @RequestMapping("/update")
    public String update() {
        return "/user/update";
    }

    //登录逻辑
    @RequestMapping("/login")
    public String login(String name, String password,Model model) {
        //使用shiro认证
        //1获取subject
        Subject subject = SecurityUtils.getSubject();
        //2封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);

        //3执行登录方法
        try {
            subject.login(token);
            //登录成功
            return "redirect:/hello";
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            //登录失败：用户名不存在
            model.addAttribute("msg","用户名不存在！");
            return  "login";
        } catch (IncorrectCredentialsException e){
            //密码错误
            e.printStackTrace();
            model.addAttribute("msg","密码错误！");
            return  "login";
        }
        //有错Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登录
//        return "/login";
    }

    //没有权限跳转的页面
    @RequestMapping("/unAuthor")
    public String unAuthor(){

        return "/unAuthor";
    }

}

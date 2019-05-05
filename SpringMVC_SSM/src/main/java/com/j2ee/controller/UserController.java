package com.j2ee.controller;

import com.j2ee.mapper.UserMapper;
import com.j2ee.po.User;
import com.j2ee.service.UserService;
import com.j2ee.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody User user) throws Exception {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper=applicationContext.getBean(UserMapper.class);
        Map<String,Object> map = new HashMap<String,Object>();

        User user1=userMapper.findUserByID(user.getUserID());
        if(user1 == null||!user.getPwd().equals(user1.getPwd())){
            map.put("code", -1);
        }
        else {
            map.put("code", 0);
            map.put("token", JwtUtil.createToken(user.getUserID()));
            map.put("name",user1.getName());
        }
        return map;
    }

    //退出登陆
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }

    //接收页面请求的json数据，并返回json格式结果
    @RequestMapping("/testJson")
    @ResponseBody
    public User testJson(@RequestBody User user){
        //打印接收的json格式数据
        System.out.println(user);
        //返回json格式的响应
        return user;
    }

}

package com.j2ee.controller;

import com.j2ee.mapper.UserMapper;
import com.j2ee.po.User;
import com.j2ee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map login(HttpServletRequest request){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserMapper userMapper=applicationContext.getBean(UserMapper.class);
        Map<String,Object> map = new HashMap<String,Object>();
        String userID = request.getParameter("userID");
        String pwd = request.getParameter("pwd");
        User user=userMapper.findUserByID(userID);
        if(user == null){
            map.put("code", -1);
        }
        else if(!user.getPwd().equals(pwd)){
            map.put("code", 0);
        }
        else {
            map.put("code", 1);
            map.put("token", "1231231231");
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

package com.j2ee.controller;

import com.auth0.jwt.interfaces.Claim;
import com.j2ee.service.UserService;
import com.j2ee.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/home",method = RequestMethod.POST)
    @ResponseBody
    public Map getHomePage(@RequestBody(required=true) Map<String,Object> map){
        String userID;
        if(map.get("token")==null){
            userID=(String) map.get("userID");
        }
        else {
            String token= (String) map.get("token");
            Map<String, Claim> a = JwtUtil.verifyToken(token);
            userID = JwtUtil.getAppUID(token);
        }
        int type=(int) map.get("type");
        Map result=userService.getHomePage(userID,type);
        return result;
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map login(@RequestBody(required=true) Map<String,Object> map) throws Exception {
        String userID=(String) map.get("userID");
        String pwd=(String) map.get("pwd");
        Map result=userService.login(userID,pwd);
        return result;
    }

    @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    @ResponseBody
    public Map changePwd(@RequestBody(required = true) Map<String,Object> map){
        String token = (String) map.get("token");
        String pwd = (String)map.get("pwd");
        String newPwd = (String)map.get("newPwd");
        Map<String, Claim> a = JwtUtil.verifyToken(token);
        Map result=userService.changePwd(token,pwd,newPwd);
        return result;
    }
}

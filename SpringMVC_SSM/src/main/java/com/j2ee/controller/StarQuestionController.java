package com.j2ee.controller;

import com.auth0.jwt.interfaces.Claim;
import com.j2ee.service.StarQuestionService;
import com.j2ee.service.UserService;
import com.j2ee.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/starQuestion")
public class StarQuestionController {
    @Autowired
    private StarQuestionService starQuestionService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/sqlist",method = RequestMethod.POST)
    @ResponseBody
    public Map starQuestionList(@RequestBody(required=true) Map<String,Object> map){
        String token = (String) map.get("token");
        Map<String, Claim> a = JwtUtil.verifyToken(token);
        int p= (int) map.get("p");
        int num= (int) map.get("num");
        int offSet=(p-1)*num;
        Map result=starQuestionService.starQuestionList(token,offSet,num);
        return result;
    }
}

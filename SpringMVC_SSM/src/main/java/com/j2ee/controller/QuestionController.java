package com.j2ee.controller;

import com.auth0.jwt.interfaces.Claim;
import com.j2ee.service.QuestionService;
import com.j2ee.service.StarAnswerService;
import com.j2ee.service.StarQuestionService;
import com.j2ee.service.UserService;
import com.j2ee.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @RequestMapping(value = "/qstar",method = RequestMethod.POST)
    @ResponseBody
    public Map questionStar(@RequestBody(required=true) Map<String,Object> map) throws ParseException {
        int questionID=(int) map.get("questionID");
        String token = (String) map.get("token");
        Map<String, Claim> a = JwtUtil.verifyToken(token);
        Map result=questionService.questionStar(questionID,token);
        return result;
    }
    @RequestMapping(value = "/questionList",method = RequestMethod.POST)
    @ResponseBody
    public Map questionList(@RequestBody(required=true) Map<String,Object> map){
        int p= (int) map.get("p");
        int num= (int) map.get("num");
        int type=(int) map.get("type");
        int offSet=(p-1)*num;
        Map result=questionService.questionList(offSet,num,type);
        return result;
    }
    @RequestMapping(value = "/questionDetail",method = RequestMethod.POST)
    @ResponseBody
    public Map questionDetail(@RequestBody(required=true) Map<String,Object> map){
        String token = (String) map.get("token");
        Map<String, Claim> a = JwtUtil.verifyToken(token);
        int questionID = (int)map.get("questionID");
        Map result=questionService.questionDetail(questionID,token);
        return result;
    }
    @RequestMapping(value = "/addQuestion",method = RequestMethod.POST)
    @ResponseBody
    public Map addQuestion(@RequestBody(required=true) Map<String,Object> map) throws ParseException {
        String token = (String) map.get("token");
        String question= (String) map.get("question");
        String detail= (String) map.get("detail");
        Map<String, Claim> a = JwtUtil.verifyToken(token);
        Map result=questionService.addQuestion(token,question,detail);
        return result;
    }
}

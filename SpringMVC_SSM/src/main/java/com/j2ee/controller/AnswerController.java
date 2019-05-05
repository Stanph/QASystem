package com.j2ee.controller;

import com.auth0.jwt.interfaces.Claim;
import com.j2ee.mapper.AnswerMapper;
import com.j2ee.po.Answer;
import com.j2ee.util.JwtUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/answer")
public class AnswerController {
    @RequestMapping(value = "/addAnswer",method = RequestMethod.POST)
    @ResponseBody
    public Map addAnswer(@RequestBody(required=true) Map<String,Object> map) throws ParseException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        AnswerMapper answerMapper=applicationContext.getBean(AnswerMapper.class);
        String token = (String) map.get("token");
        Map<String, Claim> a = JwtUtil.verifyToken(token);
        String userID = JwtUtil.getAppUID(token);
        Answer answer1=new Answer();
        answer1.setQuestionID((int)map.get("questionID"));
        answer1.setUserID(userID);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime();
        int changeTime = (int) (time / 1000);
        answer1.setCreateTime(changeTime);
        answer1.setAnswer((String)map.get("answer"));
        int result=answerMapper.addAnswer(answer1);
        int answerID=answerMapper.findLargestAnswerID();
        Map<String,Object> map1=new HashMap<String ,Object>();
        if(result>0){
            map1.put("code",0);
            map1.put("answerID",answerID);
        }
        else{
            map1.put("code",-1);
        }
        return map1;
    }
}

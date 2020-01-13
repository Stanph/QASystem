package com.j2ee.controller;

import com.auth0.jwt.interfaces.Claim;
import com.j2ee.service.AnswerService;
import com.j2ee.service.StarAnswerService;
import com.j2ee.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Map;

/**
 * @author haopan
 */
@Controller
@RequestMapping(value = "/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;
    @Autowired
    private StarAnswerService starAnswerService;
    @RequestMapping(value = "/astar",method = RequestMethod.POST)
    @ResponseBody
    public Map answerStar(@RequestBody(required=true) Map<String,Object> map) throws ParseException {
        int answerID=(int) map.get("answerID");
        String token = (String) map.get("token");
        Map<String, Claim> a= JwtUtil.verifyToken(token);
        Map<String,Object> result=answerService.answerStar(answerID,token);
        return result;
    }
    @RequestMapping(value = "/addAnswer",method = RequestMethod.POST)
    @ResponseBody
    public Map addAnswer(@RequestBody(required=true) Map<String,Object> map) throws ParseException {
        String token = (String) map.get("token");
        int questionID=(int) map.get("questionID");
        String answer=(String) map.get("answer");
        Map<String, Claim> a = JwtUtil.verifyToken(token);
        Map result=answerService.addAnswer(token,questionID,answer);
        return result;
    }
}

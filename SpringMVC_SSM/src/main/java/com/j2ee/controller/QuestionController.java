package com.j2ee.controller;

import com.j2ee.mapper.QuestionMapper;
import com.j2ee.po.Question;
import org.apache.ibatis.annotations.Param;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController {
    @RequestMapping(value = "/questionList",method = RequestMethod.POST)
    @ResponseBody
    public List<Question> questionList( int p,  int num){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionMapper questionMapper=applicationContext.getBean(QuestionMapper.class);
        int offSet=(p-1)*num;
        List<Question> question = questionMapper.findAllQuestion(offSet,num);
        for(int i=0;i<question.size();i++){
            Question question1=question.get(i);
            String detail=question1.getDetail();
            String smallDetail=detail.substring(0,200);
            question1.setDetail(smallDetail);
        }
        return question;
    }
    @RequestMapping(value = "/questionDetail",method = RequestMethod.POST)
    @ResponseBody
    public Question questionDetail(int questionID){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionMapper questionMapper=applicationContext.getBean(QuestionMapper.class);
        Question question=questionMapper.findQuestionWithAnswers(questionID);   //没有回答时 无结果
        return question;
    }
    @RequestMapping(value = "/addQuestion",method = RequestMethod.POST)
    @ResponseBody
    public Map addQuestion(String token,String question,String detail) throws ParseException {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        QuestionMapper questionMapper=applicationContext.getBean(QuestionMapper.class);
        int before= questionMapper.findLargestQuestionID(); //无记录时 会出错
        Question question1=new Question();
        question1.setUserID("31601111");
        question1.setQuestion(question);
        question1.setDetail(detail);
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        long time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date).getTime();
        int changeTime = (int) (time / 1000);
        question1.setCreateTime(changeTime);
        questionMapper.addQuestion(question1);
        int after=questionMapper.findLargestQuestionID();
        Map<String,Object> map=new HashMap<String ,Object>();
        if(after==before+1){
            map.put("code",0);
            map.put("questionID",after);
        }
        else{
            map.put("code",-1);
        }
        return map;
    }
}

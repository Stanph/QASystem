package com.j2ee.service.impl;

import com.j2ee.mapper.AnswerMapper;
import com.j2ee.mapper.StarAnswerMapper;
import com.j2ee.po.Answer;
import com.j2ee.po.StarAnswer;
import com.j2ee.service.AnswerService;
import com.j2ee.util.JwtUtil;
import com.j2ee.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haopan
 */
@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;
    @Autowired
    private StarAnswerMapper starAnswerMapper;

    @Override
    public Map answerStar(int answerID, String token) throws ParseException {
        Map<String,Object> map=new HashMap<>(1);
        map.put("code",0);
        String userID = JwtUtil.getAppUID(token);
        //查找是否已点赞
        StarAnswer starAnswer=new StarAnswer();
        starAnswer.setAnswerID(answerID);
        starAnswer.setUserID(userID);
        int  starOrNot = starAnswerMapper.starOrNot(starAnswer);
        if(starOrNot==0){
            //点赞
            starAnswer.setStarTime(TimeUtil.getIntTime());
            starAnswerMapper.addAnswerStar(starAnswer);
            answerMapper.addStar(answerID);
            map.put("code",1);
        }
        else{
            //取消
            starAnswerMapper.deleteAnswerStar(starAnswer);
            answerMapper.deleteStar(answerID);
            map.put("code",1);
        }
        return map;
    }

    @Override
    public Map addAnswer(String token, int questionID, String answer) throws ParseException {
        String userID = JwtUtil.getAppUID(token);
        Answer a=new Answer();
        a.setQuestionID(questionID);
        a.setUserID(userID);
        int createTime = TimeUtil.getIntTime();
        a.setCreateTime(createTime);
        a.setAnswer(answer);
        int result=answerMapper.addAnswer(a);
        int answerID=answerMapper.findLargestAnswerID();
        Map<String,Object> map= new HashMap<>(2);
        if(result>0){
            map.put("code",0);
            map.put("answerID",answerID);
        }
        else{
            map.put("code",-1);
        }
        return map;
    }
}

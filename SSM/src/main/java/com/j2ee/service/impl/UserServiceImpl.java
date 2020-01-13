package com.j2ee.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.j2ee.mapper.AnswerMapper;
import com.j2ee.mapper.QuestionMapper;
import com.j2ee.mapper.UserMapper;
import com.j2ee.po.Answer;
import com.j2ee.po.Question;
import com.j2ee.po.User;
import com.j2ee.service.UserService;
import com.j2ee.util.JwtUtil;
import com.j2ee.util.KafkaConsumerTest;
import com.j2ee.util.Md5Util;
import com.j2ee.util.TimeUtil;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    // 注解注入 UserMapper
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private AnswerMapper answerMapper;
    @Override
    public Map getHomePage(String userID,int type) {
        Map<String,Object> map=new HashMap<>();
        if(type==0){
            List<Question> questions=questionMapper.findQuestionByUserID(userID);
            for(int i=0;i<questions.size();i++){
                Question question=questions.get(i);
                //限制问题详情200字
                String detail=question.getDetail();
                int x=detail.length();
                if(x>200){
                    x=200;
                }
                String smallDetail=detail.substring(0,x);
                question.setDetail(smallDetail);
                User user=userMapper.findUserByID(question.getUserID());
                question.setUserName(user.getName());
                question.setShowTime(TimeUtil.getShowTime(question.getCreateTime()));
            }
            map.put("questions",questions);
        }
        else{
            List<Answer> answers=answerMapper.findAnswerByUserID(userID);
            for(int i=0;i<answers.size();i++){
                Answer answer=answers.get(i);
                String detail=answer.getAnswer();
                int x=detail.length();
                if(x>200){
                    x=200;
                }
                String smallDetail=detail.substring(0,x);
                answer.setAnswer(smallDetail);
                User user=userMapper.findUserByID(answer.getUserID());
                answer.setUserName(user.getName());
                answer.setShowTime(TimeUtil.getShowTime(answer.getCreateTime()));
            }
            map.put("answers",answers);
        }
        map.put("userName",userMapper.findUserByID(userID).getName());
        return map;
    }

    @Override
    public Map login(String userID, String pwd) throws Exception {
        Map<String,Object> map = new HashMap<>();
        User user=userMapper.findUserByID(userID);
        pwd= Md5Util.getHash(pwd);
        if(user == null||!pwd.equals(user.getPwd())){
            map.put("code", -1);
        }
        else {
            map.put("code", 0);
            map.put("token", JwtUtil.createToken(userID));
            map.put("name",user.getName());
            if(user.getUserType()==0){
                KafkaConsumerTest test1 = new KafkaConsumerTest("question",userID);
                List<String> list=test1.consume();
                List<Question> questionList=new ArrayList<>();
                for(int i=0;i<list.size();i++){
                    JSONObject object=JSONObject.parseObject(list.get(i));
                    Question question=new Question();
                    question.setQuestionID(object.getInteger("questionID"));
                    question.setQuestion(object.getString("question"));
                    questionList.add(question);
                    System.out.println(question);
                }
                map.put("question",questionList);
            }

        }
        return map;
    }

    @Override
    public Map changePwd(String token, String pwd, String newPwd) {
        String userID = JwtUtil.getAppUID(token);
        User user=userMapper.findUserByID(userID);
        Map<String,Object> map=new HashMap<>();
        newPwd=Md5Util.getHash(newPwd);
        pwd=Md5Util.getHash(pwd);
        if(newPwd==null||newPwd.length()==0||!pwd.equals(user.getPwd())){
            map.put("code",-1);
        }
        else{
            user.setPwd(newPwd);
            userMapper.updateUserPwd(user);
            map.put("code",0);
        }
        return map;
    }
}

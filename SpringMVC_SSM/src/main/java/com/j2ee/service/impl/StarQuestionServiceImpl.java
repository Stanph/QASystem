package com.j2ee.service.impl;

import com.j2ee.mapper.StarQuestionMapper;
import com.j2ee.mapper.UserMapper;
import com.j2ee.po.StarQuestion;
import com.j2ee.po.User;
import com.j2ee.service.StarQuestionService;
import com.j2ee.util.JwtUtil;
import com.j2ee.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
public class StarQuestionServiceImpl implements StarQuestionService {
    @Autowired
    private StarQuestionMapper starQuestionMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map starQuestionList(String token, int offSet, int num) {
        Map map=new HashMap();
        String userID = JwtUtil.getAppUID(token);
        List<StarQuestion> starQuestions=starQuestionMapper.findUserStarQuestion(userID,offSet,num);
        for(int i=0;i<starQuestions.size();i++){
            StarQuestion starQuestion=starQuestions.get(i);
            //限制问题详情200字
            String detail=starQuestion.getQuestion().getDetail();
            int x=detail.length();
            if(x>200){
                x=200;
            }
            String smallDetail=detail.substring(0,x);
            starQuestion.getQuestion().setDetail(smallDetail);
            User user=userMapper.findUserByID(starQuestion.getQuestion().getUserID());
            starQuestion.getQuestion().setUserName(user.getName());
            starQuestion.getQuestion().setShowTime(TimeUtil.getShowTime(starQuestion.getStarTime()));
        }
        map.put("starQuestions",starQuestions);
        return map;
    }
}

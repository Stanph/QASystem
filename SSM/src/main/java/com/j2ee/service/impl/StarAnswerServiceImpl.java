package com.j2ee.service.impl;

import com.j2ee.mapper.StarAnswerMapper;
import com.j2ee.mapper.UserMapper;
import com.j2ee.po.StarAnswer;
import com.j2ee.po.User;
import com.j2ee.service.StarAnswerService;
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
public class StarAnswerServiceImpl implements StarAnswerService {
    @Autowired
    private StarAnswerMapper starAnswerMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map starAnswerList(String token, int offSet, int num) {
        Map map=new HashMap();
        String userID = JwtUtil.getAppUID(token);
        List<StarAnswer> starAnswers=starAnswerMapper.findUserStarAnswer(userID,offSet,num);
        for(int i=0;i<starAnswers.size();i++){
            StarAnswer starAnswer=starAnswers.get(i);
            //限制问题详情200字
            String detail=starAnswer.getAnswer().getAnswer();
            int x=detail.length();
            if(x>200){
                x=200;
            }
            String smallDetail=detail.substring(0,x);
            starAnswer.getAnswer().setAnswer(smallDetail);
            User user=userMapper.findUserByID(starAnswer.getAnswer().getUserID());
            starAnswer.getAnswer().setUserName(user.getName());
            starAnswer.getAnswer().setShowTime(TimeUtil.getShowTime(starAnswer.getStarTime()));
        }
        map.put("starAnswers",starAnswers);
        return map;
    }
}

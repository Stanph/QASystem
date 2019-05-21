package com.j2ee.service.impl;

import com.j2ee.mapper.QuestionMapper;
import com.j2ee.mapper.StarAnswerMapper;
import com.j2ee.mapper.StarQuestionMapper;
import com.j2ee.mapper.UserMapper;
import com.j2ee.po.*;
import com.j2ee.service.QuestionService;
import com.j2ee.util.JwtUtil;
import com.j2ee.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)

public class QuestionServiceImpl implements QuestionService {
	@Autowired
	private QuestionMapper questionMapper;
	@Autowired
	private StarQuestionMapper starQuestionMapper;
	@Autowired
	private StarAnswerMapper starAnswerMapper;
	@Autowired
	private UserMapper userMapper;

	@Override
	public Map questionStar(int questionID, String token) throws ParseException {
		Map<String,Object> map=new HashMap<>();
		map.put("code",0);
		String userID = JwtUtil.getAppUID(token);
		//查找是否已点赞
		StarQuestion starQuestion=new StarQuestion();
		starQuestion.setQuestionID(questionID);
		starQuestion.setUserID(userID);
		int  starOrNot = starQuestionMapper.starOrNot(starQuestion);
		if(starOrNot==0){
			//点赞
			starQuestion.setStarTime(TimeUtil.getIntTime());
			starQuestionMapper.addQuestionStar(starQuestion);
			questionMapper.addStar(questionID);
			map.put("code",1);
		}
		else{
			//取消
			starQuestionMapper.deleteQuestionStar(starQuestion);
			questionMapper.deleteStar(questionID);
			map.put("code",1);
		}
		return map;
	}

	@Override
	public Map questionList(int offSet, int num, int type) {
		Map map=new HashMap();
		List<Question> questions;
		if(type==0){
			questions = questionMapper.findAllQuestion(offSet,num);
		}
		else{
			questions = questionMapper.findAllQuestionByStar(offSet,num);
		}
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
		return map;
	}

	@Override
	public Map questionDetail(int questionID, String token) {
		Map map=new HashMap();
		String userID = JwtUtil.getAppUID(token);
		//找到问题和回答
		Question question=questionMapper.findQuestionWithAnswers(questionID);
		//设置当前用户是否点赞
		StarQuestion starQuestion=new StarQuestion();
		StarAnswer starAnswer=new StarAnswer();
		starQuestion.setQuestionID(questionID);
		starQuestion.setUserID(userID);
		int starOrNot = starQuestionMapper.starOrNot(starQuestion);
		question.setStarOrNot(starOrNot);
		//设置问题用户名
		User user=userMapper.findUserByID(question.getUserID());
		question.setUserName(user.getName());
		//设置问题时间
		String showTime= TimeUtil.getShowTime(question.getCreateTime());
		question.setShowTime(showTime);
		//设置回答的时间、用户名、是否点赞
		List<Answer> answers=question.getAnswers();
		for(int i=0;i<answers.size();i++){
			showTime= TimeUtil.getShowTime(answers.get(i).getCreateTime());
			answers.get(i).setShowTime(showTime);
			user=userMapper.findUserByID(answers.get(i).getUserID());
			answers.get(i).setUserName(user.getName());
			starAnswer.setAnswerID(answers.get(i).getAnswerID());
			starAnswer.setUserID(userID);
			starOrNot=starAnswerMapper.starOrNot(starAnswer);
			answers.get(i).setStarOrNot(starOrNot);
		}
		question.setAnswers(answers);
		//增加浏览量
		questionMapper.updatePageviews(question.getQuestionID());
		map.put("question",question);
		return map;
	}

	@Override
	public Map addQuestion(String token, String question, String detail) throws ParseException {
		String userID = JwtUtil.getAppUID(token);
		Question q=new Question();
		q.setUserID(userID);
		q.setQuestion(question);
		q.setDetail(detail);
		int changeTime = TimeUtil.getIntTime();
		q.setCreateTime(changeTime);
		int result=questionMapper.addQuestion(q);
		int questionID=questionMapper.findLargestQuestionID();
		Map<String,Object> map=new HashMap<>(2);
		if(result>0){
			map.put("code",0);
			map.put("questionID",questionID);
		}

		else{
			map.put("code",-1);
		}
		return map;
	}
}

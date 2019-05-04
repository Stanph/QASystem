package com.j2ee.service.impl;

import com.j2ee.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.j2ee.po.Question;
import com.j2ee.service.QuestionService;

import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{
	@Autowired
	private QuestionMapper questionMapper;

	@Override
	public Question findQuestionWithAnswers(int questionID) {
		return this.questionMapper.findQuestionWithAnswers(questionID);
	}

	@Override
	public int findLargestQuestionID(){
		return this.questionMapper.findLargestQuestionID();
	}
	@Override
	public Question findQuestionByQuestionID(int questionID) {
		return this.questionMapper.findQuestionByQuestionID(questionID);
	}

	@Override
	public List<Question> findAllQuestion(int offSet,int pageSize) {
		return this.questionMapper.findAllQuestion(offSet,pageSize);
	}

	@Override
	public List<Question> findQuestionByUserID(String string) {
		return this.questionMapper.findQuestionByUserID(string);
	}

	@Override
	public void addQuestion(Question question) {

		this.questionMapper.addQuestion(question);
	}

	@Override
	public void deleteQuestion(int i) {
		this.questionMapper.deleteQuestion(i);
	}

	@Override
	public void updateStarOrPageviews(Question question) {
		this.questionMapper.updateStarOrPageviews(question);
	}

}

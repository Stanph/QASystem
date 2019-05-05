package com.j2ee.mapper;

import java.util.List;

import com.j2ee.po.Answer;

public interface AnswerMapper {
	public int findLargestAnswerID();
	public List<Answer> findAnswerByQuestionID(int i);
	public List<Answer> findAnswerByUserID(String userID);
	public int addAnswer(Answer answer);
	public void deleteAnswer(int answerID);
	public void updateStar(Answer answer);
}

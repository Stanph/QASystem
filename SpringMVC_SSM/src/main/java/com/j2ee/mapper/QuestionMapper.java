package com.j2ee.mapper;

import java.util.List;

import com.j2ee.po.Question;

public interface QuestionMapper {
	Question findQuestionWithAnswers(int questionID);
	int findLargestQuestionID();
	Question findQuestionByQuestionID(int questionID);
	List<Question> findAllQuestion(int offSet, int pageSize);
	List<Question> findAllQuestionByStar(int offSet, int pageSize);
	List<Question> findQuestionByUserID(String string);
	int addQuestion(Question question);
	void deleteQuestion(int i);
	void addStar(int questionID);
	void deleteStar(int questionID);
	void updatePageviews(int questionID);
}

package com.j2ee.mapper;

import java.util.List;

import com.j2ee.po.Question;

public interface QuestionMapper {
	public Question findQuestionWithAnswers(int questionID);

	public int findLargestQuestionID();

	public Question findQuestionByQuestionID(int questionID);

	public List<Question> findAllQuestion(int offSet, int pageSize);
	//查找用户的问题
	public List<Question> findQuestionByUserID(String string);
	//添加问题
	public int addQuestion(Question question);
	//删除问题
	public void deleteQuestion(int i);
	//更新问题赞数或流量
	public void updateStarOrPageviews(Question question);
}

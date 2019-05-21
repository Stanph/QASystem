package com.j2ee.mapper;

import java.util.List;

import com.j2ee.po.Answer;

public interface AnswerMapper {
	/**
	 *
	 * @return
	 */
	int findLargestAnswerID();

	/**
	 * @param i
	 * @return
	 */
	List<Answer> findAnswerByQuestionID(int i);

	/**
	 * @param userID
	 * @return
	 */
	List<Answer> findAnswerByUserID(String userID);

	/**
	 * @param answer
	 * @return
	 */
	int addAnswer(Answer answer);

	/**
	 * @param answerID
	 */
	void deleteAnswer(int answerID);

	/**
	 * @param answerID
	 */
	void addStar(int answerID);

	/**
	 * @param answerID
	 */
	void deleteStar(int answerID);
}

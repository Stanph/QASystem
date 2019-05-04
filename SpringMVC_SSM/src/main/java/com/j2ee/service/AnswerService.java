package com.j2ee.service;

import com.j2ee.po.Answer;

import java.util.List;

public interface AnswerService {
    public int findLargestAnswerID();
    public List<Answer> findAnswerByQuestionID(int i);
    public List<Answer> findAnswerByUserID(String userID);
    public void addAnswer(Answer answer);
    public void deleteAnswer(int answerID);
    public void updateStar(Answer answer);
}

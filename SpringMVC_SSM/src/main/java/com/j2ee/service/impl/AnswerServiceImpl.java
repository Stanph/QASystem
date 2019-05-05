package com.j2ee.service.impl;

import com.j2ee.mapper.AnswerMapper;
import com.j2ee.po.Answer;
import com.j2ee.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerMapper answerMapper;

    @Override
    public int findLargestAnswerID(){
        return this.answerMapper.findLargestAnswerID();
    }
    @Override
    public List<Answer> findAnswerByQuestionID(int i) {
        return this.answerMapper.findAnswerByQuestionID(i);
    }

    @Override
    public List<Answer> findAnswerByUserID(String userID) {
        return this.answerMapper.findAnswerByUserID(userID);
    }

    @Override
    public int addAnswer(Answer answer) {
        int result=this.answerMapper.addAnswer(answer);
        return  result;
    }

    @Override
    public void deleteAnswer(int answerID) {
        this.answerMapper.deleteAnswer(answerID);
    }

    @Override
    public void updateStar(Answer answer) {
        this.answerMapper.updateStar(answer);
    }
}

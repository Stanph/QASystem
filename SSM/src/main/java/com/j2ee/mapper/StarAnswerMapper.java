package com.j2ee.mapper;

import com.j2ee.po.StarAnswer;

import java.util.List;

public interface StarAnswerMapper {
    List<StarAnswer> findUserStarAnswer(String userID, int offSet, int pageSize);
    int addAnswerStar(StarAnswer starAnswer);
    int deleteAnswerStar(StarAnswer starAnswer);
    int starOrNot(StarAnswer starAnswer);

}

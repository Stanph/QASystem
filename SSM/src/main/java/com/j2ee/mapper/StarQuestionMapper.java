package com.j2ee.mapper;

import com.j2ee.po.StarQuestion;

import java.util.List;

public interface StarQuestionMapper {
    List<StarQuestion> findUserStarQuestion(String userID, int offSet, int pageSize);
    int addQuestionStar(StarQuestion starQuestion);
    int deleteQuestionStar(StarQuestion starQuestion);
    int starOrNot(StarQuestion starQuestion);
}

package com.j2ee.service;

import java.util.Map;

public interface StarQuestionService {
    Map starQuestionList(String token, int offSet, int num);
}

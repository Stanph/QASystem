package com.j2ee.service;

import java.text.ParseException;
import java.util.Map;


public interface QuestionService {
	Map questionStar(int questionID, String token) throws ParseException;
	Map questionList(int offSet, int num, int type);
	Map questionDetail(int questionID, String token);
	Map addQuestion(String token, String question, String detail) throws ParseException;
}

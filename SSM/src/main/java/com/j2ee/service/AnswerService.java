package com.j2ee.service;

import java.text.ParseException;
import java.util.Map;

/**
 * @author haopan
 */
public interface AnswerService {
    /**
     * @param answerID
     * @param token
     * @return
     */
    Map answerStar(int answerID, String token) throws ParseException;
    Map addAnswer(String token, int questionID, String answer) throws ParseException;

}
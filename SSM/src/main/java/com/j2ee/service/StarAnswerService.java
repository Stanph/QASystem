package com.j2ee.service;

import java.util.Map;

public interface StarAnswerService {
    Map starAnswerList(String token, int offSet, int num);

}

package com.j2ee.service;

import java.util.Map;

/**
 *   用户Service接口
 */
public interface UserService {
    Map getHomePage(String userID,int type);
    Map login(String userID, String pwd) throws Exception;
    Map changePwd(String token, String pwd, String newPwd);
}

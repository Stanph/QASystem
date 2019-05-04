package com.j2ee.test;

import com.j2ee.po.User;
import com.j2ee.service.UserService;
import org.springframework.context.ApplicationContext;
import  org.springframework.context.support.ClassPathXmlApplicationContext;
public class TransactionTest {
    public static void main(String args[]) {
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = act.getBean(UserService.class);
        User user = new User();
        user.setName("陈胡数4");
        user.setPwd("123456");
        user.setUserID("31601114");
        user.setUserType(0);
        userService.addUser(user);
    }
}

package com.j2ee.service.impl;

import com.j2ee.mapper.UserMapper;
import com.j2ee.po.User;
import com.j2ee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    // 注解注入 UserMapper
    @Autowired
    private UserMapper userMapper;
    // 通过ID寻找客户
    public User findUserByID(String userID) {
        return this.userMapper.findUserByID(userID);
    }
    // 添加客户
    public void addUser(User user) {
        this.userMapper.addUser(user);
    }
    //删除用户
    public void deleteUser(String userID) {
        this.userMapper.deleteUser(userID);
    }
    //更新密码
    public void updateUserPwd(User user){
        this.userMapper.updateUserPwd(user);
    }
}

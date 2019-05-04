package com.j2ee.service;
import com.j2ee.po.User;
/**
 *   用户Service接口
 */
public interface UserService {
    // 通过账号查找User
    public User findUserByID(String userID);
    //添加用户
    public void addUser(User user);
    //删除用户
    public void deleteUser(String userID);
    //更新密码
    public void updateUserPwd(User user);
}

package com.j2ee.mapper;
import com.j2ee.po.User;

public interface UserMapper {
	// 通过账号查找User
	User findUserByID(String userID);
	//添加用户
	void addUser(User user);
	//删除用户
	void deleteUser(String userID);
	//更新密码
	void updateUserPwd(User user);
}

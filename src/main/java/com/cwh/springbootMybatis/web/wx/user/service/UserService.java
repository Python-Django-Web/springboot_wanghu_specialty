package com.cwh.springbootMybatis.web.wx.user.service;

import com.cwh.springbootMybatis.web.wx.user.entity.User;


public interface UserService {

	
	User selectUserByOpenId(String openid);
	
	Integer updataUser(User user);
	
	Integer insertUser(User user);
}

package com.cwh.springbootMybatis.web.wx.user.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwh.springbootMybatis.web.wx.user.entity.User;
import com.cwh.springbootMybatis.web.wx.user.mapper.UserMapper;
import com.cwh.springbootMybatis.web.wx.user.service.UserService;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private  UserMapper UserMapper;
	
	@Override
	public User selectUserByOpenId(String openid) {
		// TODO Auto-generated method stub
		return UserMapper.selectUserByOpenId(openid);
	}

	@Override
	public Integer updataUser(User user) {
		// TODO Auto-generated method stub
		return UserMapper.updataUser(user);
	}

	@Override
	public Integer insertUser(User user) {
		// TODO Auto-generated method stub
		return UserMapper.insertUser(user);
	}

}

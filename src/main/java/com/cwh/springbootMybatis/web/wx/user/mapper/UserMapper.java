package com.cwh.springbootMybatis.web.wx.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cwh.springbootMybatis.web.wx.user.entity.User;


@Mapper
public interface UserMapper {

		User selectUserByOpenId(String openid);
	
		Integer updataUser(User user);
		
		Integer insertUser(User user);
	
}

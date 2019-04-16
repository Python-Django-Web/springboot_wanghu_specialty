package com.cwh.springbootMybatis.weixin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.cwh.springbootMybatis.weixin.entity.AccessToken;

@Mapper
public interface AccessTokenMapper {
	
	int update(Map<String,Object> map);
	
	List<AccessToken> select(AccessToken accessToken); 
}

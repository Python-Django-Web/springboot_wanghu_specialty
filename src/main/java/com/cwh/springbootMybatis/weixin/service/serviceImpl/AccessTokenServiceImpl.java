package com.cwh.springbootMybatis.weixin.service.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwh.springbootMybatis.weixin.entity.AccessToken;
import com.cwh.springbootMybatis.weixin.mapper.AccessTokenMapper;
import com.cwh.springbootMybatis.weixin.service.AccessTokenService;





@Service
@Transactional
public class AccessTokenServiceImpl implements AccessTokenService {
	@Autowired
	public  AccessTokenMapper accessTokenMapper ;
	
	
	public int update(Map<String, Object> map) {
		return accessTokenMapper.update(map);
	}

	
	public List<AccessToken> listById(AccessToken accessToken) {
		System.out.println(accessToken);
		List<AccessToken> a=accessTokenMapper.select(accessToken);
		System.out.println("asdsa"+a);
		return accessTokenMapper.select(accessToken);
	}
}

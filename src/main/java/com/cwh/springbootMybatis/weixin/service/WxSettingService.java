package com.cwh.springbootMybatis.weixin.service;


import org.springframework.stereotype.Service;

import com.cwh.springbootMybatis.weixin.entity.WxSetting;

@Service
public interface WxSettingService {


	public  WxSetting selectByPrimaryKey(Integer id);

	public    int updateByPrimaryKeySelective(WxSetting record);
	
	
}

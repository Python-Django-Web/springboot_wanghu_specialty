package com.cwh.springbootMybatis.weixin.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cwh.springbootMybatis.weixin.entity.WxSetting;
import com.cwh.springbootMybatis.weixin.mapper.WxSettingMapper;
import com.cwh.springbootMybatis.weixin.service.WxSettingService;


@Service
@Transactional
public class WxSettingServiceImpl implements WxSettingService {
	@Autowired
	private WxSettingMapper wxSettingMapper ;

	public  WxSetting selectByPrimaryKey(Integer id){
		return wxSettingMapper.selectByPrimaryKey(id);
	}

	public    int updateByPrimaryKeySelective(WxSetting record){
		int rs = 0;
		try {
			rs = wxSettingMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			rs = -1;
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return rs;
	}
	
	
}

package com.cwh.springbootMybatis.weixin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cwh.springbootMybatis.weixin.entity.WxSetting;

@Mapper
public interface WxSettingMapper {
	public  int deleteByPrimaryKey(Integer id);

	public  int insert(WxSetting record);

	public  int insertSelective(WxSetting record);

	public   WxSetting selectByPrimaryKey(Integer id);

	public int updateByPrimaryKeySelective(WxSetting record);

	public  int updateByPrimaryKey(WxSetting record);
}
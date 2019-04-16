package com.cwh.springbootMybatis.weixin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cwh.springbootMybatis.weixin.entity.WxNews;



@Mapper
public interface WxNewsMapper {

	public List<WxNews> AllNewsSelect();
	public WxNews  NewsSelectCt_key(String ct_key);
	public Integer InsertNews(WxNews returnNews);
	public Integer UpdateNews(WxNews returnNews);
	public Integer deleteNews(String ct_key);
	
	
	
}

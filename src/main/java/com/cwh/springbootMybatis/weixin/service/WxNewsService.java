package com.cwh.springbootMybatis.weixin.service;

import java.util.List;

import com.cwh.springbootMybatis.weixin.entity.WxNews;

public interface WxNewsService {

	
	public List<WxNews> AllNewsSelect();
	public WxNews  NewsSelectCt_key(String ct_key);
	public Integer InsertNews(WxNews returnNews);
	public Integer UpdateNews(WxNews returnNews);
	public Integer deleteNews(String ct_key);
}

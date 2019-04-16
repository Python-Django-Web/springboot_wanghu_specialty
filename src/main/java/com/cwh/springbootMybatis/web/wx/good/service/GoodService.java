package com.cwh.springbootMybatis.web.wx.good.service;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.web.wx.good.entity.Good;

public interface GoodService {

	GeneralReturn insertGood(Good good);
	
	GeneralReturn updateGood(Good good);
	
	GeneralReturn GetGoodById(Integer id);
	
	GeneralReturn LimitCount(Good good);
}

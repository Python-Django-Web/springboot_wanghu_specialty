package com.cwh.springbootMybatis.web.wx.shop.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.util.SessionOpenId;
import com.cwh.springbootMybatis.web.wx.shop.entity.Shop;

/**
 * 购物车
 * @author wanghu
 *
 */
@RestController
@RequestMapping("/web/")
public class ShopController extends SessionOpenId{

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	GeneralReturn GRn;
	
	@RequestMapping("/insertShop")
	public void insertShop(Shop shop){
		
	}
}

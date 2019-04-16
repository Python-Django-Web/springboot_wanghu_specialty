package com.cwh.springbootMybatis.web.wx.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.util.SessionOpenId;
import com.cwh.springbootMybatis.web.wx.order.entity.Order;
/**
 * 订单
 * @author wanghu
 *
 */
@RestController
@RequestMapping("/web/")
public class OrderController extends SessionOpenId{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	GeneralReturn GRn;
	
	@RequestMapping("/insertOrder")
	public void insertOrder(Order order){
		
	}
	
}

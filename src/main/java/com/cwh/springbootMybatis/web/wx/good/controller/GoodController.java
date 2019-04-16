package com.cwh.springbootMybatis.web.wx.good.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.util.SessionOpenId;
import com.cwh.springbootMybatis.web.wx.good.entity.Good;
import com.cwh.springbootMybatis.web.wx.good.service.GoodService;
/**
 * 商品
 * @author wanghu
 *
 */
@RestController
@RequestMapping("/web/")
public class GoodController extends SessionOpenId {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	GeneralReturn GRn;

	@Autowired
	private GoodService GoodService;
	/**
	 * 
	 * @param id
	 * @return GeneralReturn
	 */
	@RequestMapping("/{id}/GetGoodById")
	public GeneralReturn GetGoodById(@PathVariable("id") Integer id){
		logger.info("进入 GetGoodById 接口： id:{}",id);	
		GRn = GoodService.GetGoodById(id);
		logger.info("返回GetGoodById接口: {}",GRn);	
		return GRn;
	}
	
	/**
	 * 
	 * @param good
	 * @param request
	 * @param response
	 * @param session
	 * @return GeneralReturn
	 */
	@RequestMapping("/insertGood")
	public GeneralReturn insertGood(Good good, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		
		logger.info("进入insertGood接口: {}", good);
		GRn = GoodService.insertGood(good);
		logger.info("返回insertGood接口: {}",GRn);	
		
		return GRn;
	}
	
	/**
	 * 
	 * @param id
	 * @param good
	 * @param request
	 * @param response
	 * @param session
	 * @return GeneralReturn
	 */
	@RequestMapping("/{id}/updateGood")
	public GeneralReturn updateGood(@PathVariable("id") Integer id, Good good,
			HttpServletRequest request, HttpServletResponse response,
			HttpSession session) {
		
		logger.info("进入updateGood接口: {}", good);
		GRn = GoodService.updateGood(good);
		logger.info("返回updateGood接口：{}",GRn);
		
		return GRn;
	}
	
	/**
	 * 
	 * @param good
	 * @return GeneralReturn
	 */
	@RequestMapping("/limit")
	public GeneralReturn limit(Good good){
		
		logger.info("进入LimitGood接口: {}", good);
		GRn = GoodService.LimitCount(good);
		logger.info("返回LimitGood接口: {}", GRn);
		
		return GRn;
	}
	
	
}

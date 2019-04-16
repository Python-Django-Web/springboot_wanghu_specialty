package com.cwh.springbootMybatis.web.wx.address.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.util.SessionOpenId;
import com.cwh.springbootMybatis.web.wx.address.entity.Address;
/**
 * 收货地址
 * @author wanghu
 *
 */
@RestController
@RequestMapping("/web/")
public class AddressController extends SessionOpenId{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	GeneralReturn GRn;
	
	/**
	 * 
	 * @param address
	 */
	@RequestMapping("/SaveAddress")
	public void SaveAddress(Address address){
		
	}
	/**
	 * 
	 */
	@RequestMapping("ChangeAddress")
	public void ChangeAddress(Address address){
		
	}
	
	/**
	 * 
	 */
	@RequestMapping("/{id}/GetAddress")
	public void GetAddress(Integer id){
		
	}
	
}

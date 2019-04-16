package com.cwh.springbootMybatis.web.pay.zfb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwh.springbootMybatis.web.pay.zfb.util.DateUtil;

@RestController
@RequestMapping
public class WIDTRout_trade_no{

	@RequestMapping("out_trade_no")
	public String out_trade_no() {
		System.out.println("访问接口：WIDTRout_trade_no/out_trade_no");
		String out_trade_no = "wan" + DateUtil.Obtain_date("yyyyMMddHHmmssSSS");
		System.out.println("返回参数out_trade_no：" + out_trade_no);
		return out_trade_no;
	}
}

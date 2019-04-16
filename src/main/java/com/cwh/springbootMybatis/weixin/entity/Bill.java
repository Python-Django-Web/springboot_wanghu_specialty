package com.cwh.springbootMybatis.weixin.entity;

import org.springframework.stereotype.Component;

@Component
public class Bill {
	private Integer id;
	private Integer memberId;// 会员Id
	private Double money;// 获得或失去金额
	private String date;// 此账单的时间
	private String pattern;// 获得金额方式(数字：1自己获得红包2下线获得红包返利3下线成为代理4自己交钱成为或升级代理商5提现6充值7从游戏获得的钱）
	private String comment;// 备注、原因
	private String indent;// 账单号
	private String state;// 订单状态
	private String topic;//标题
	private String type;//呼吸效果
	private String fee;//手续费
	private String advertisingImg;//广告商头像
	private String fromSoftware;//从微信或者支付宝支付(0微信1支付宝）
	private String phoneIp;//手机Ip

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getIndent() {
		return indent;
	}

	public void setIndent(String indent) {
		this.indent = indent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getAdvertisingImg() {
		return advertisingImg;
	}

	public void setAdvertisingImg(String advertisingImg) {
		this.advertisingImg = advertisingImg;
	}

	public String getFromSoftware() {
		return fromSoftware;
	}

	public void setFromSoftware(String fromSoftware) {
		this.fromSoftware = fromSoftware;
	}

	public String getPhoneIp() {
		return phoneIp;
	}

	public void setPhoneIp(String phoneIp) {
		this.phoneIp = phoneIp;
	}


}

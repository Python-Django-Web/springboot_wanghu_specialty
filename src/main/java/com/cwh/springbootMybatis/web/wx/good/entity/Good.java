package com.cwh.springbootMybatis.web.wx.good.entity;

import com.cwh.springbootMybatis.util.Page;

public class Good extends Page{
	
	private Integer id;
	
	private String goodId;
	
	private String goodName;
	
	private String goodType;
	
	private String goodLogin;
	
	private String goodMain;
	
	private Double goodPrice;
	
	private Integer goodSales;
	
	private String addTime;
	
	private Integer del;

	private String bannerUrl;
	
	private String loginRand;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoodId() {
		return goodId;
	}

	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}

	public String getGoodName() {
		return goodName;
	}

	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}

	public String getGoodType() {
		return goodType;
	}

	public void setGoodType(String goodType) {
		this.goodType = goodType;
	}

	public String getGoodLogin() {
		return goodLogin;
	}

	public void setGoodLogin(String goodLogin) {
		this.goodLogin = goodLogin;
	}

	public String getGoodMain() {
		return goodMain;
	}

	public void setGoodMain(String goodMain) {
		this.goodMain = goodMain;
	}



	public Double getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(Double goodPrice) {
		this.goodPrice = goodPrice;
	}

	public Integer getGoodSales() {
		return goodSales;
	}

	public void setGoodSales(Integer goodSales) {
		this.goodSales = goodSales;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getDel() {
		return del;
	}

	public void setDel(Integer del) {
		this.del = del;
	}

	

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getLoginRand() {
		return loginRand;
	}

	public void setLoginRand(String loginRand) {
		this.loginRand = loginRand;
	}

	@Override
	public String toString() {
		return "Good [id=" + id + ", goodId=" + goodId + ", goodName="
				+ goodName + ", goodType=" + goodType + ", goodLogin="
				+ goodLogin + ", goodMain=" + goodMain + ", goodPrice="
				+ goodPrice + ", goodSales=" + goodSales + ", addTime="
				+ addTime + ", del=" + del + ", bannerUrl=" + bannerUrl
				+ ", loginRand=" + loginRand + "]";
	}


	
	
}

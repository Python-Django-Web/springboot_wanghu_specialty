package com.cwh.springbootMybatis.weixin.entity;

public class AccessToken {
	Integer id ;
	String access_token ;
	Long add_time;
	private int expires_In;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Long getAdd_time() {
		return add_time;
	}
	public void setAdd_time(Long add_time) {
		this.add_time = add_time;
	}
	public int getExpires_In() {
		return expires_In;
	}
	public void setExpires_In(int expires_In) {
		this.expires_In = expires_In;
	}
	
	
	
	
}

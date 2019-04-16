package com.cwh.springbootMybatis.web.wx.user.entity;
/**
 * 微信用户
 * @author wanghu
 *
 */
public class User {
	/**
	 * id
	 */
	private int id;
	/**
	 * 用户id
	 */
	private String userid;
	/**
	 * 微信openid
	 */
	private String openid;
	/**
	 * 微信昵称
	 */
	private String username;
	/**
	 * 微信头像
	 */
	private String userhead;
	/**
	 * 注册时间
	 */
	private String regtime;
	/**
	 * 登录时间
	 */
	private String addTime;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 真实姓名
	 */
	private String realname;
	/**
	 * 删除状态
	 */
	private String del;

	
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getUserid() {
		return userid;
	}




	public void setUserid(String userid) {
		this.userid = userid;
	}




	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserhead() {
		return userhead;
	}

	public void setUserhead(String userhead) {
		this.userhead = userhead;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}




	public String getAddTime() {
		return addTime;
	}




	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}




	public String getDel() {
		return del;
	}




	public void setDel(String del) {
		this.del = del;
	}




	@Override
	public String toString() {
		return "User [id=" + id + ", userid=" + userid + ", openid=" + openid
				+ ", username=" + username + ", userhead=" + userhead
				+ ", regtime=" + regtime + ", addTime=" + addTime + ", mobile="
				+ mobile + ", realname=" + realname + ", del=" + del + "]";
	}

	


	
	
}
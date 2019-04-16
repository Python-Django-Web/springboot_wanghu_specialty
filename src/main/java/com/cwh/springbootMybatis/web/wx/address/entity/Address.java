package com.cwh.springbootMybatis.web.wx.address.entity;

/**
 * 收货地址
 * 
 * @author wanghu
 *
 */
public class Address {

	/**
	 * id
	 */
	private String id;
	/**
	 * 用户id
	 */
	private String userid;
	/**
	 * 收货姓名
	 */
	private String name;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 收货地址
	 */
	private String address;
	/**
	 * 收货街道门牌号
	 */
	private String street;
	/**
	 * 是否为默认
	 */
	private String def;
	/**
	 * 删除状态
	 */
	private String del;
	/**
	 * 添加时间
	 */
	private String addtime;
	/**
	 * 修改时间
	 */
	private String mtime;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getDef() {
		return def;
	}
	public void setDef(String def) {
		this.def = def;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	
	@Override
	public String toString() {
		return "Address [id=" + id + ", userid=" + userid + ", name=" + name
				+ ", mobile=" + mobile + ", address=" + address + ", street="
				+ street + ", def=" + def + ", del=" + del + ", addtime="
				+ addtime + ", mtime=" + mtime + "]";
	}

	
}

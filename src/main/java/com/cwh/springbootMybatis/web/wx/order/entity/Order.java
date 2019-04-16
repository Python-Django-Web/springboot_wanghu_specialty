package com.cwh.springbootMybatis.web.wx.order.entity;
/**
 * 订单表
 * @author wanghu
 *
 */
public class Order {
	/**
	 * id
	 */
	private String id;
	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 购物车id
	 */
	private String shopId;
	/**
	 * 留言
	 */
	private String leave;
	/**
	 * 是否包邮
	 */
	private String postage;
	/**
	 * 邮费
	 */
	private String packagefee;
	/**
	 * 地址id
	 */
	private String adressId;
	/**
	 * 总额
	 */
	private String sumMoney;
	/**
	 * 删除类型 1未删除 -1 删除
	 */
	private String del;
	/**
	 * 订单类型
	 */
	private String orderType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getLeave() {
		return leave;
	}
	public void setLeave(String leave) {
		this.leave = leave;
	}
	public String getPostage() {
		return postage;
	}
	public void setPostage(String postage) {
		this.postage = postage;
	}
	public String getPackagefee() {
		return packagefee;
	}
	public void setPackagefee(String packagefee) {
		this.packagefee = packagefee;
	}
	public String getAdressId() {
		return adressId;
	}
	public void setAdressId(String adressId) {
		this.adressId = adressId;
	}
	public String getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}
	public String getDel() {
		return del;
	}
	public void setDel(String del) {
		this.del = del;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", orderId=" + orderId + ", userId="
				+ userId + ", shopId=" + shopId + ", leave=" + leave
				+ ", postage=" + postage + ", packagefee=" + packagefee
				+ ", adressId=" + adressId + ", sumMoney=" + sumMoney
				+ ", del=" + del + ", orderType=" + orderType + "]";
	}

	
}

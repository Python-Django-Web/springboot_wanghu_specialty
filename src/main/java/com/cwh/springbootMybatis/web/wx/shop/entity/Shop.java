package com.cwh.springbootMybatis.web.wx.shop.entity;
/**
 * 购物车
 * @author 王虎
 *
 */
public class Shop {
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 购物车id（用来识别那些商品是一个购物车里面的）
	 */
	private String shopId;
	/**
	 * 用户id
	 */
	private String userId;
	/**
	 * 商品id
	 */
	private String goodId;
	/**
	 * 购物车商品数量
	 */
	private Integer goodSum;
	/**
	 * 添加商品时间
	 */
	private String addTime;
	/**
	 * 删除状态
	 */
	private String del;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getGoodId() {
		return goodId;
	}
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}
	public Integer getGoodSum() {
		return goodSum;
	}
	public void setGoodSum(Integer goodSum) {
		this.goodSum = goodSum;
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
		return "shop [id=" + id + ", shopId=" + shopId + ", userId=" + userId
				+ ", goodId=" + goodId + ", goodSum=" + goodSum + ", addTime="
				+ addTime + ", del=" + del + "]";
	}

	
	
}

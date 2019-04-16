package com.cwh.springbootMybatis.web.wx.shop.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cwh.springbootMybatis.web.wx.shop.entity.Shop;

@Mapper
public interface ShopMapper {

	Integer insertShop(Shop shop);
	
	Integer updateShop(Shop shop);
	
	Shop selectShopById(Integer id);
	
	
	
}

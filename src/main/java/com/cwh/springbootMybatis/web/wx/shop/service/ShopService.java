package com.cwh.springbootMybatis.web.wx.shop.service;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.web.wx.shop.entity.Shop;

public interface ShopService {

	GeneralReturn SaveShop(Shop shop);
	
	GeneralReturn ChangeShop(Shop shop);
	
	GeneralReturn GetShopById(Integer id);
	
}

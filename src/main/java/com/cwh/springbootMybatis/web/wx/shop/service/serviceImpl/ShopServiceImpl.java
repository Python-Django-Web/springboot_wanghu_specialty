package com.cwh.springbootMybatis.web.wx.shop.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.web.wx.shop.entity.Shop;
import com.cwh.springbootMybatis.web.wx.shop.mapper.ShopMapper;
import com.cwh.springbootMybatis.web.wx.shop.service.ShopService;

@Service("ShopServiceImpl")
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopMapper ShopMapper;

	@Override
	public GeneralReturn SaveShop(Shop shop) {
		// TODO Auto-generated method stub
		Integer intShop = ShopMapper.insertShop(shop);
		return GeneralReturn.build(intShop,
				intShop == 1 ? "Insert Shop success" : "Insert Shop faill");
	}

	@Override
	public GeneralReturn ChangeShop(Shop shop) {
		// TODO Auto-generated method stub
		Integer intShop = ShopMapper.updateShop(shop);
		return GeneralReturn.build(intShop,
				intShop == 1 ? "Update Shop success" : "Update Shop faill");
	}

	@Override
	public GeneralReturn GetShopById(Integer id) {
		// TODO Auto-generated method stub
		Shop shop = ShopMapper.selectShopById(id);
		return GeneralReturn.build(shop != null ? 1 : 0,
				shop != null ? "Select Shop success" : "Select Shop faill",
				shop);
	}

}

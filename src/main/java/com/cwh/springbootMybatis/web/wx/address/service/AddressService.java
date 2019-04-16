package com.cwh.springbootMybatis.web.wx.address.service;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.web.wx.address.entity.Address;


public interface AddressService {

	GeneralReturn SaveAddress(Address address);
	
	GeneralReturn ChangeAddress(Address address);

	GeneralReturn GetAddressById(Integer  id);
}

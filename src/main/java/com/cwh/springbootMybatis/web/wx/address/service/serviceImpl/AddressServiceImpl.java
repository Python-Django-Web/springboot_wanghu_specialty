package com.cwh.springbootMybatis.web.wx.address.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.web.wx.address.entity.Address;
import com.cwh.springbootMybatis.web.wx.address.mapper.AddressMapper;
import com.cwh.springbootMybatis.web.wx.address.service.AddressService;

@Service("AddressServiceImpl")
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper AddressMapper;

	@Override
	public GeneralReturn SaveAddress(Address address) {
		// TODO Auto-generated method stub
		Integer intAddress = AddressMapper.insertAddress(address);
		return GeneralReturn.build(intAddress,
				intAddress == 1 ? "Insert Address success"
						: "Insert Address faill");
	}

	@Override
	public GeneralReturn ChangeAddress(Address address) {
		// TODO Auto-generated method stub
		Integer intAddress = AddressMapper.updateAddress(address);
		return GeneralReturn.build(intAddress,
				intAddress == 1 ? "Update Address success"
						: "Update Address faill");
	}

	@Override
	public GeneralReturn GetAddressById(Integer id) {
		// TODO Auto-generated method stub
		Address address = AddressMapper.selectAddressById(id);
		return GeneralReturn.build(address != null ? 1 : 0,
				address != null ? "Select Address success"
						: "Select Address faill", address);
	}

}

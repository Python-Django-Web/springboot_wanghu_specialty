package com.cwh.springbootMybatis.web.wx.address.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.cwh.springbootMybatis.web.wx.address.entity.Address;

@Mapper
public interface AddressMapper {

	Integer insertAddress(Address address);
	
	Integer updateAddress(Address address);

	Address selectAddressById(Integer  id);
}

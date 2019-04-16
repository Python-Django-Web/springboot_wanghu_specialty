package com.cwh.springbootMybatis.web.wx.good.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cwh.springbootMybatis.web.wx.good.entity.Good;

@Mapper
public interface GoodMapper {

	int insertGood(Good good);

	int updateGood(Good good);

	Good GetGoodById(Integer id);
	
	List<Good> Limit(Good good);
	int Count(Good good);
	
	
}

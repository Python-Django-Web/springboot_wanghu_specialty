package com.cwh.springbootMybatis.weixin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cwh.springbootMybatis.weixin.entity.button.Button;

@Mapper
public interface ButtonMapper {
    int deleteByPrimaryKey(String id);

    int insert(Button record);

    int insertSelective(Button record);
    
    List<Button> select(Button record);
    
    int selectCount(Button record);
    
    Button selectByPrimaryKey(String id);
    
    int sort(Button record);
    
    int updateByPrimaryKeySelective(Button record);

    int updateByPrimaryKey(Button record);
}
package com.cwh.springbootMybatis.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.cwh.springbootMybatis.weixin.entity.button.Button;




@Service
@Transactional
public interface ButtonService {
	

	public int deleteByPrimaryKey(String id) ;

	public int insert(Button record) ;

	public Button selectByPrimaryKey(String id) ;
	public int selectCount(Button record);
	public List<Button>  select(Button record) ;
	public int updateByPrimaryKey(Button record) ;
	
	public int updateByPrimaryKeySelective(Button record) ;
	public int sort(Button record) ;
}

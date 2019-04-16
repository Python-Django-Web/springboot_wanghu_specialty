package com.cwh.springbootMybatis.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * 
 * @author wanghu
 * spring 定时器
 *
 */
@Component
public class SchedulerTask2 {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Scheduled(fixedRate = 5000)//每隔5秒执行一次
	private void dateTask() {
		LOG.info("SchedulerTask2 : " + new Date().toString());
	}
	
	@Scheduled(fixedRate = 300000)//每隔5分钟执行一次
	private void dateTask2(){
		LOG.info("---------------------------------------------"+new Date().toString());
		LOG.info("---------------------------------------------"+new Date().toString());
	}
}

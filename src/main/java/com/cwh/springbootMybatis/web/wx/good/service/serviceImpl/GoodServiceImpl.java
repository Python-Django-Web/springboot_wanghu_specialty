package com.cwh.springbootMybatis.web.wx.good.service.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cwh.springbootMybatis.util.GeneralReturn;
import com.cwh.springbootMybatis.util.PageUtil;
import com.cwh.springbootMybatis.util.RDateUtils;
import com.cwh.springbootMybatis.util.uuidUtil;
import com.cwh.springbootMybatis.web.wx.good.entity.Good;
import com.cwh.springbootMybatis.web.wx.good.mapper.GoodMapper;
import com.cwh.springbootMybatis.web.wx.good.service.GoodService;

@Transactional
@Service("GoodServiceImpl")
public class GoodServiceImpl implements GoodService {

	@Autowired
	private GoodMapper GoodMapper;

	@Override
	public GeneralReturn insertGood(Good good) {
		// TODO Auto-generated method stub
		good.setGoodId(uuidUtil.getRandom());
		good.setAddTime(RDateUtils.formatDate2(new Date()));
		good.setDel(1);
		good.setGoodSales(0);
		int intGood = GoodMapper.insertGood(good);

		return GeneralReturn.build(intGood,
				intGood == 1 ? "Insert Good success" : "Insert Good faill",
				good);
	}

	@Override
	public GeneralReturn updateGood(Good good) {
		// TODO Auto-generated method stub

		int intGood = GoodMapper.updateGood(good);

		return GeneralReturn.build(intGood,
				intGood == 1 ? "Update Good success" : "Update Good faill");
	}

	@Override
	public GeneralReturn LimitCount(Good good) {
		// TODO Auto-generated method stub
		int intCount = GoodMapper.Count(good);
		Map<String, Integer> map = PageUtil.pager(good.getCurrentPage(), 3,
				intCount);
		good.setPageSize(3);
		good.setCurrentNum(PageUtil.currentNum(good.getCurrentPage(), 3));
		List<Good> list = GoodMapper.Limit(good);

		Map<String, Object> m = new HashMap<String, Object>();
		m.put("data", list);
		m.put("total", map.get("total"));
		m.put("totalPage", map.get("totalPage"));
		m.put("currentPage", map.get("currentPage"));

		return GeneralReturn.build(1, "success", m);
	}

	@Override
	public GeneralReturn GetGoodById(Integer id) {
		// TODO Auto-generated method stub
		Good good = GoodMapper.GetGoodById(id);
		return GeneralReturn.build(good != null ? 1 : 0,
				good != null ? "Select Good success" : "Select Good faill",
				good);
	}

}

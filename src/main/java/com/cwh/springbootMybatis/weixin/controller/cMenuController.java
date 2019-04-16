package com.cwh.springbootMybatis.weixin.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;














import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cwh.springbootMybatis.weixin.entity.button.Button;
import com.cwh.springbootMybatis.weixin.entity.button.ViewButton;
import com.cwh.springbootMybatis.weixin.service.ButtonService;
import com.cwh.springbootMybatis.weixin.util.MenuUtil;
import com.google.gson.Gson;





@Controller
@RequestMapping
public class cMenuController {
	@Autowired
	private ButtonService buttonService ;
//	private Button button;
	private Map<String,Object> map = new HashMap<String, Object>();
	Gson gson = new Gson();
	
	@ResponseBody
	@RequestMapping(value = "/main/cMenu")
	public String cMenu(Button button){
		button.setLevel(1);
		List<Button> mainBtn =	buttonService.select(button);
		button.setLevel(2);
		Map<String,Object> map = new HashMap<String, Object>();
		for(int i = 0;i < mainBtn.size();i++){
			button.setSuper_id(mainBtn.get(i).getId());
			List<Button> btn =	buttonService.select(button);
			List<Object> vl = new ArrayList<Object>();
			if(btn.size()==0){
				mainBtn.get(i).setUrl(mainBtn.get(i).getValue());
			}
			else{
			for(int m = 0;m < btn.size();m++){
			if(btn.get(m).getType().equals("view")){
				ViewButton vb = new ViewButton();
				vb.setName(btn.get(m).getName());
				vb.setType(btn.get(m).getType());
				vb.setUrl(btn.get(m).getValue());
				vl.add(vb);
				}
			}
			mainBtn.get(i).setSub_button(vl);
			}
		}
		map.put("button",mainBtn);
	Map<String,Object> m=	MenuUtil.createMenu(map);
		return gson.toJson(m);
	}
}

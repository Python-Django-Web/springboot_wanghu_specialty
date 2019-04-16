package com.cwh.springbootMybatis.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cwh.springbootMybatis.weixin.entity.Item;
import com.cwh.springbootMybatis.weixin.entity.RequestTextMessage;
import com.cwh.springbootMybatis.weixin.entity.WxNews;
import com.cwh.springbootMybatis.weixin.service.WxNewsService;
import com.cwh.springbootMybatis.weixin.util.CheckUtil;
import com.cwh.springbootMybatis.weixin.util.ReplyMessage;

/**
 * 微信公众号项目的连接接口
 * 
 * 使用注解注册servlet
 * 
 * @WebServlet(urlPatterns="/servlet/WeiXinServlet", description="通过注解注册")
 *  不指定name的情况下，name默认值为类全路径，即org.springboot.sample.servlet.WeiXinServlet
 * @author wanghu
 */

@WebServlet(urlPatterns = "/servlet/WeiXinServlet.action", description = "通过注解注册")
public class WeiXinServlet extends HttpServlet {

	// 日志
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	@Autowired
	private WxNewsService WxNewsService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		this.logger.info("echostr{}", echostr);
		System.out.println(signature);
		PrintWriter out = resp.getWriter();
		// 连接token的重要工具类
		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
			out.flush();
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("-----进入扫描二维码-----");

		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();

		try {

			String message = null;
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = resp.getWriter();
			String wxMsgXml = IOUtils.toString(req.getInputStream(), "utf-8");
			System.out.println("<<<<" + wxMsgXml);

			boolean eventType = wxMsgXml.contains("Event");// 如果包含，则是触发事件
			RequestTextMessage textMsg = null;

			StringBuffer replyMsg = new StringBuffer();
			System.out.println(!eventType);
			String returnXml = null;

			if (!eventType) { // 信息交互事件
				System.out.println("没有event值》》》》》" + !eventType);
				System.out.println("-------------进入自动回复--------------");

				textMsg = ReplyMessage.getRequestTextMessage(wxMsgXml);
				String ct_key = textMsg.getContent().trim();
				System.out.println("receive:" + ct_key);

				WxNews news = WxNewsService.NewsSelectCt_key("subscribe");

				if (news.getType() == 1) {
					System.out.println("------------单条文本回复-----------");

					replyMsg.append(news.getContent());
					returnXml = ReplyMessage.getReplyTextMessage(
							replyMsg.toString(), textMsg.getFromUserName(),
							textMsg.getToUserName());

					System.out.println("用户输入：《" + ct_key + "》微信给用户返回：《"
							+ news.getContent() + ">");

				} else if (news.getType() == 2) {
					System.out.println("---------------多条图文本回复--------------");

					List<Item> articleList = new ArrayList<>();
					Item item = null;
					if (news.getIntro().contains("-=")) {// 多条图文信息
						String[] title = news.getTitle().split("-=");
						String[] intro = news.getIntro().split("-=");
						String[] pic_url = news.getPic_url().split("-=");
						String[] url = news.getUrl().split("-=");
						for (int i = 0; i < intro.length; i++) {
							item = new Item();
							item.setTitle(title[i]); // 标题
							item.setDescription(intro[i]);// 介绍
							item.setPicUrl(pic_url[i]); // 图片链接
							item.setUrl(url[i]); // 链接指向
							articleList.add(item);
						}
					} else { // 单条图文信息
								// 单条图文的信息回复
						System.out
								.println("---------------单条图文本回复--------------");
						item = new Item();
						item.setTitle(news.getTitle()); // 标题
						item.setDescription(news.getIntro());// 介绍
						item.setPicUrl(news.getPic_url()); // 图片链接
						item.setUrl(news.getUrl()); // 链接指向
						articleList.add(item);

					}
					returnXml = ReplyMessage.getReplyTuwenMessage(
							textMsg.getFromUserName(), textMsg.getToUserName(),
							articleList);
				}

			} else {
				System.out.println("有event值》》》》》" + !eventType);
				System.out.println("-------------进入关注回复--------------");

				textMsg = ReplyMessage.getRequestFocus(wxMsgXml);
				if (textMsg.getEvent().equals("subscribe")) { // 关注事件
					WxNews news = WxNewsService.NewsSelectCt_key("subscribe");
					System.out.println();
					replyMsg.append(news.getContent());
					logger.info("关注回复->" + news.getContent());
					returnXml = ReplyMessage.getReplyTextMessage(
							replyMsg.toString(), textMsg.getFromUserName(),
							textMsg.getToUserName());

					System.out.println("关注回复：《" + news.getContent() + "》");
				}

			}
			System.out.println(message);
			out.print(message);

		} catch (Exception e) {
		} finally {
			out.print("");
			out.flush();
			out.close();
		}
	}

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}

}

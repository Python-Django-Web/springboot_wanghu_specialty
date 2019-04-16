package com.cwh.springbootMybatis.weixin.entity.message;

public class ForMessage {

	private String touser;
	private String msgtype;
	private text text;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public text getText() {
		return text;
	}
	public void setText(text text) {
		this.text = text;
	}
	@Override
	public String toString() {
		return "ForMessage [touser=" + touser + ", msgtype=" + msgtype
				+ ", text=" + text + "]";
	}

	
	
}

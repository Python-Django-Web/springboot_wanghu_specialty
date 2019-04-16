package com.cwh.springbootMybatis.weixin.entity;

public class WxNews {

		private String ct_key;

	    private Integer type;

	    private String content;

	    private String title;
	    
	    private String intro;

	    private String pic_url;

	    private String url;

	    private String delete_status;
	    
	    private String addtime;
	    
	    private String remark;
	    
	    
		public String getAddtime() {
			return addtime;
		}

		public void setAddtime(String addtime) {
			this.addtime = addtime;
		}

		public String getRemark() {
			return remark;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}

		public String getCt_key() {
			return ct_key;
		}

		public void setCt_key(String ct_key) {
			this.ct_key = ct_key;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getIntro() {
			return intro;
		}

		public void setIntro(String intro) {
			this.intro = intro;
		}

		public String getPic_url() {
			return pic_url;
		}

		public void setPic_url(String pic_url) {
			this.pic_url = pic_url;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getDelete_status() {
			return delete_status;
		}

		public void setDelete_status(String delete_status) {
			this.delete_status = delete_status;
		}

		@Override
		public String toString() {
			return "ReturnNews [ct_key=" + ct_key + ", type=" + type
					+ ", content=" + content + ", title=" + title + ", intro="
					+ intro + ", pic_url=" + pic_url + ", url=" + url
					+ ", delete_status=" + delete_status + ", addtime="
					+ addtime + ", remark=" + remark + "]";
		}

	    
}

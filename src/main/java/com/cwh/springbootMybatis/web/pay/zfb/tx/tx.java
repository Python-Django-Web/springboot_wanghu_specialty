package com.cwh.springbootMybatis.web.pay.zfb.tx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayFundTransOrderQueryModel;
import com.alipay.api.domain.AlipayFundTransToaccountTransferModel;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alibaba.fastjson.JSONObject;
import com.cwh.springbootMybatis.web.pay.zfb.util.AlipayConfig;
import com.cwh.springbootMybatis.web.pay.zfb.util.DateUtil;
@RestController
@RequestMapping("/tx/")
public class tx{
	public static AlipayClient alipayClient;

	static {
		alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl,
				AlipayConfig.app_id, AlipayConfig.merchant_private_key,
				AlipayConfig.format, AlipayConfig.charset,
				AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	}
	
		public static String transPrecreatePay(AlipayFundTransToaccountTransferModel model) throws AlipayApiException {
		AlipayFundTransToaccountTransferResponse response = transferToResponse(model);
		return response.getBody();
	}
	
		public static AlipayFundTransToaccountTransferResponse transferToResponse(AlipayFundTransToaccountTransferModel model) throws AlipayApiException{
	        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
	        request.setBizModel(model);
	        return alipayClient.execute(request);
	    }
	
		
		/**
	     * 单笔转账到支付宝账户
	     * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.54Ty29&treeId=193&articleId=106236&docType=1
	     * @param content
	     * @return
	     * @throws AlipayApiException
	     */
	    public static boolean transfer(AlipayFundTransToaccountTransferModel model) throws AlipayApiException{
	        AlipayFundTransToaccountTransferResponse response = transferToResponse(model);
	        String result = response.getBody();
	        System.out.println("输出转账返回参数："+result);
	        System.out.println("transfer result>"+result);
	        if (response.isSuccess()) {
	            return true;
	        } else {
	            //调用查询接口查询数据
	        	System.out.println("进入查询接口》》》");
	            JSONObject jsonObject = JSONObject.parseObject(result);
	            String out_biz_no = jsonObject.getJSONObject("alipay_fund_trans_toaccount_transfer_response").getString("out_biz_no");
	            AlipayFundTransOrderQueryModel queryModel = new AlipayFundTransOrderQueryModel();
	            model.setOutBizNo(out_biz_no);
	            boolean isSuccess = transferQuery(queryModel);
	            if (isSuccess) {
	                return true;
	            }
	        }
	        return false;
	    }
		
	    /**
	     * 转账查询接口
	     * @param content
	     * @return
	     * @throws AlipayApiException
	     */
	    public static boolean transferQuery(AlipayFundTransOrderQueryModel model) throws AlipayApiException{
	        AlipayFundTransOrderQueryResponse response = transferQueryToResponse(model);
	        System.out.println("transferQuery result>"+response.getBody());
	        if(response.isSuccess()){
	            return true;
	        }
	        return false;
	    }
	    public static AlipayFundTransOrderQueryResponse transferQueryToResponse(AlipayFundTransOrderQueryModel model) throws AlipayApiException{
	        AlipayFundTransOrderQueryRequest request = new AlipayFundTransOrderQueryRequest();
	        request.setBizModel(model);
	        return alipayClient.execute(request);
	    }
	    /**
	     * 单笔转账到支付宝账户
	     * https://doc.open.alipay.com/docs/doc.htm?spm=a219a.7629140.0.0.54Ty29&treeId=193&articleId=106236&docType=1
	     */
	    @RequestMapping("transfer")
	    public boolean transfer(String payeeAccount,String amount,String remark) {
	        boolean isSuccess = false;
	        //初始化参数
	        String total_payeeAccount = payeeAccount;
	        String total_amount = amount;
	        String total_remark = remark;
	        
	        
	        AlipayFundTransToaccountTransferModel model = new AlipayFundTransToaccountTransferModel();
	        model.setOutBizNo(DateUtil.Obtain_date("yyyyMMddHHmmssSSS"));//生成订单号
	        model.setPayeeType("ALIPAY_LOGONID");//固定值
	        model.setPayeeAccount(total_payeeAccount);//转账收款账户
	        model.setAmount(total_amount);
	        model.setPayerShowName("天津优点广告传媒有限公司");//对方账号或转账公司名称
	        model.setPayerRealName("");//账户真实名称
	        model.setRemark(total_remark);//提现理由
	        
	        try {
	            isSuccess = transfer(model);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return isSuccess;
	    }
}

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="description" content="">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="viewport" content="user-scalable=0" />
		<link rel="stylesheet" href="../css/swiper/swiper-3.4.2.min.css">	
		<link rel="stylesheet" href="css/index.css">
		<script type="text/javascript" src="../js/common.js"></script>
		<script type="text/javascript" src="../js/jQuery/jquery-1.11.2.js"></script>
		<script type="text/javascript" src="../js/jQuery/jweixin-1.0.0.js"></script>
		<script type="text/javascript" src="../js/swiper/swiper-3.4.2.min.js"></script>
		<title>我的二维码</title>
	</head>
	<body>
	<div class="entire">
		<div class="logo"></div>
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide" style="background-image: url(images/a1.jpg);"></div>
				<div class="swiper-slide" style="background-image: url(images/a2.jpg);"></div>
				<div class="swiper-slide" style="background-image: url(images/a3.jpg);"></div>
			</div>
			<div class="swiper-pagination"></div>
		</div>
		<!-- <p class="code_time">二维码过期时间：<span>2018-02-05</span>&nbsp;<span>12:00:18</span>(有效期<span>30</span>天)</p> -->
			<!--二维码扫描开始-->
		<input type="hidden" id="q" value="${param.oppen_id} ">
			<div class="code_pic" id="img">
			<img src="" id="QR">
			</div>
		<!--二维码扫描结束-->
		<div class="bottom_txt"></div>
	</div>
	

	</body>
	<script type="text/javascript">
	 var swiper = new Swiper(".swiper-container",{
		autoplay:5000,
	 	effect : 'coverflow',
		slidesPerView: 1.5,
		centeredSlides: true,
		pagination : '.swiper-pagination'
	 })
	 		var oppen_id= $("#q").val();
	 	//	alert(oppen_id);
	 	 $.ajax({
 			url:'/EasyLoan/QRcode/create',
 			data : 'oppen_id=' + oppen_id,
 			type:"post",
			dataType:"json",
			  success: function(data) { 
			 	$("#QR").attr('src', data.wx_QR_url);
			  }
			  });
	</script>
	<script>
	
    $().ready(function(){
        initPage();
        
    });
    function initPage() {
      // alert(window.location.href);/***用于获得当前连接url用**/
        /***用户点击分享到微信圈后加载接口接 http://wx.lcjtstnz.com/chihaodian/w/share.html口*******/
        $.post("http://m.jyzhsw.cn/EasyLoan/fenxiang/share.html",{"url":window.location.href},function(data,status){
         data=eval("("+data+")");
         //   console.log(data.appid+" "+data.timestamp+" "+data.nonceStr+" "+data.signature);
            wx.config({
                debug: true,
                appId: data.appid,
                timestamp:data.timestamp,
                nonceStr:data.nonceStr,
                signature:data.signature,
                jsApiList: [
                    'checkJsApi',
                    'onMenuShareTimeline',
                    'onMenuShareAppMessage',
                ]
            });
           
            var shareTitle = "关注，分享，签到，即可每天领取两次现金红包！";
            var shareImg = "http://m.tjaxej.com/EasyLoan/amall/QRcode/images/15132182051.jpg";
            
            wx.ready(function(){
       //   alert("准备分享");
                wx.onMenuShareTimeline({
                    title : shareTitle, // 分享标题
                    link : '', // 分享链接
                    imgUrl : shareImg, // 分享图标
                    success : function() {
                        // 用户确认分享后执行的回调函数
                        alert("分享成功");
                        addOne();
                    },
                    cancel : function() {
                        // 用户取消分享后执行的回调函数
                        alert("分享取消");
                    }
                });
                     //朋友  
          	 wx.onMenuShareAppMessage({  
               title: shareTitle, // 分享标题  
               desc: '', // 分享描述  
               link: '', // 分享链接  
               imgUrl: shareImg, // 分享图标  
               type: 'link', // 分享类型,music、video或link，不填默认为link  
               dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空  
               success: function () {  
                   // 用户确认分享后执行的回调函数  
                   alert("分享成功");  
                      addOne();
               },  
               cancel: function () {  
                   // 用户取消分享后执行的回调函数  
                   alert("取消分享");  
               }  
           });  
                
             //   wx.hideOptionMenu();/***隐藏分享菜单****/
            });
        });
    }
 
     function addOne(){
    $.ajax({
    	url : '/EasyLoan/fenxiang/addOneClick',
			type : "post",
			dataType : "json",
			success : function(data) {
				if(data==0){
					alert("今天成功添加了一次抽奖机会！");
				}else{
					alert("您今天的抽奖机会已经加过了！");
				}
			} 
			
    });
    }
    
</script>
</html>

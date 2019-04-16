var vm = new Vue({
    el: "#rrapp",
    data: {
        user: {},
        //服务
        supply: {},
        //my 申请的服务列表
        supplys:[],
        img:"",	
        //购物车总量
        gross: 0,
        carShow: true
    },
    mounted: function () {
        this.$nextTick(function () {

        })
    },
    filters: {
        serialSel: function (val) {
            return val + "" + new Date().getTime();
        }
    },
    computed: {
    },
    methods: {
        /**
         * 上传图片
         * @param event
         */
        uploadImg: function (event) {
            var obj = event.target;
            var $file = $(obj);
            var fileObj = $file[0];
            var windowURL = window.URL || window.webkitURL;
            var dataURL;
            var $img = $(obj).parent();
            if(fileObj && fileObj.files && fileObj.files[0]){
                dataURL = windowURL.createObjectURL(fileObj.files[0]);
                $img.css('background-image',"url("+dataURL+")");
            }
            $img.find("span").html("");

            var inputId = obj.id;
            //根据id判断生产环境还是办公环境
        	$.ajaxFileUpload({
				url : '../web/FileUpload', // 需要链接到服务器地址
				secureuri : false,
				fileElementId : "file", // 文件选择框的id属性
				dataType : 'text', // 服务器返回的格式，可以是json
				success : function(rs) // 相当于java中try语句块的用法
				{
				console.log("我的图" + rs)
					 var url = rs
							.replace(
									'<pre style="word-wrap: break-word; white-space: pre-wrap;">',
									"");
					url = url.replace("</pre>", "")
					console.log("我的图" + url)
					var urlImg = JSON.parse(url);
						console.log("我的图" + urlImg.data)
						vm.img=urlImg.data
					/*$("#img").attr("src",
							"../" + urlImg.data); */
				},
				error : function(data, status, e) // 相当于java中catch语句块的用法
				{
					alert('失败');

				}
			})
       /*   $.ajaxFileUpload({
                url: "../File/upload_File",
                type: "post",
                data: {
                 
                },
                secureuri: false,
                fileElementId: obj.id,
                dataType: 'json',
                success: function (r, status) {
                    //文件路径
                    if (inputId == "supply_file") {
                        vm.supply.supply_file = r.data;
                        vm.supply.supply_id = supply_id;
                        vm.editUploadFile();
                    }
                },
                error: function (r, status, e) {
                    alert(e);
                }
            })*/
        },


        /**
         * my harlan link 首页个人中心 订单页面跳转
         * 根据用户类别
         */
      /*  lnkPersonalCenter: function () {
            if (vm.user == null || typeof vm.user == "undefined") {
                alert("error");
                return;
            }
            //stage1:英文采购商
            if (vm.user.biz_type == 'buyer_en') {
                window.location.href = "order_buyer.html";
            }
            //stage2:英文供应商 - 个人
            if (vm.user.biz_type == 'seller_en' && vm.user.user_type == "per") {
                window.location.href = "sale_person_info.html";
            }
            //stage3:英文供应商 - 公司
            if (vm.user.biz_type == 'seller_en' && vm.user.user_type == "com") {
                window.location.href = "sale_firm_info.html";
            }
        },*/
        /**
         * 修改上传文件
         */
        editUploadFile : function () {
            $.ajax({
                url: '/supplyServ/edit',
                data: vm.supply,
                type: "post",
                dataType: "json",
                success : function (r) {
                    if(r.code == 0){
                        alert("uploaded successfully");
                    }else {
                        alert("fail to upload");
                    }
                }
            })
        },
        /**
         * 提示
         */
        reminder : function () {
            alert("后台未审核!");
        }
    }

});



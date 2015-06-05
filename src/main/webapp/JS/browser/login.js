//登录
function login(){
	var loginname = $.trim($("#login").find("#username").val());
	var password = $.trim($("#login").find("#password").val());
	var loginSalt = $.trim($("#login").find("#loginSalt").val());
	
	if(loginname==''){
		$.messager.alert("提示","请输入登录账号");
		return;
	}else if(password==''){
		$.messager.alert("提示","请输入登录密码");
		return;
	}
	loginname = CryptoJS.SHA1(loginname);
	password = CryptoJS.HmacSHA1(CryptoJS.SHA1(password).toString(),loginSalt).toString();
	
	$('#login').form('submit', {
	    onSubmit: function(param){
	        param.loginname = loginname;
	        param.password = password;
	        param.loginSalt = loginSalt;
	    },
	    success:function(data){
	    	var jsonData = eval('(' + data + ')');
	        if(jsonData.code==1){
	        	if(jsonData.op.code==1){
	        		//alert("登录成功");
	        		window.location.href="main?m=toMain";
	        	}else if(jsonData.op.code==0){
	        		alert("账号或密码不正确");
	        	}else{
	        		alert("未知错误");
	        	}
	        }else{
	        	alert("程序异常");
	        }
	    }
	});
	
	//alert($.md5("123456")=="e10adc3949ba59abbe56e057f20f883e");
	//alert($.sha1("123456")=="7c4a8d09ca3762af61e59520943dc26494f8941b");
	//$("#login").form("submit");
	//alert(CryptoJS.HmacMD5('what do ya want for nothing?', 'Jefe').toString());
	//alert(CryptoJS.HmacSHA1('what do ya want for nothing?', '521c46fc-e8f8-439e-bca4-08908fe4f06a').toString());
}

//重置
function reset(){
	$("#login").form("reset");
}
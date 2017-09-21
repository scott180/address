$().ready(function(){
	var addressId = $("#addressId").val();
	if(addressId && addressId!=0){
		userDetail(addressId);
	}
	$("#userSubmit").unbind("click");
	$("#userSubmit").bind("click",function(){
		userSubmit();
		/*if(checkParams()){
			userSubmit();
		};*/
	});
});

//修改用户时查看详情
function userDetail(addressId){
	$.ajax({
		   type: "POST",
		   url: "address/userDetail",
		   data: "addressId="+addressId,
		   success: function(msg){
			   //id addressId,userid,name,tel,email
			   var arr = msg.split(",");
			   $("#name").val(arr[2]);
			   $("#tel").val(arr[3]);
			   $("#email").val(arr[4]);
		   }
		});
}

//重置
function clearUser(){
	$("#userName").val('');
	$("#tel").val('');
	$("#email").val('');
}

//提交前检查参数
function checkParams(){
	
	var name = $.trim($("#name").val());
	if(name.length==0){
		alert("用户名称不能为空！");
		return false;
	}
	
	var tel =$("#tel").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(!myreg.test(tel)) { 
	    alert('请输入有效的手机号码！'); 
	    return false; 
	} 
	
	var email = $("#email").val();
	 var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	 if(!reg.test(email)){
		 alert('请输入有效的电子邮件！');
		 return false;
	 }
	 return true;
}

//提交
function userSubmit(){
	var userId = $("#userId").val();
	var name = $.trim($("#name").val());
	if(name.length==0){
		alert("用户名称不能为空！");
		return;
	}
	
	var tel =$("#tel").val();
	var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	if(!myreg.test(tel)) { 
	    alert('请输入有效的手机号码！'); 
	    return; 
	} 
	
	var email = $("#email").val();
	 var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
	 if(!reg.test(email)){
		 alert('请输入有效的电子邮件！');
		 return;
	 }
	 
	var addressId = $("#addressId").val();
	var data = "userId="+userId+"&name="+name+"&tel="+tel+"&email="+email+"&addressId="+addressId;

	if(confirm("确认提交？")){
		$.ajax({
		   type: "POST",
		   url: "address/updateUser",
		   data: data,
		   dataType : "json",
		   success: function(msg){
			   /*setTimeout(function(){
				  
			   }, 1000);*/
			   if (confirm("提交成功。是否继续添加？")) {  
		           return;
		        };  
		        window.close();
			    window.opener.location.href="http://localhost:8080/Address/userList";
		   }
		});
	}
}

$().ready(function(){
	$('#userAllBox').bind("click",userAll_check_box_click);
	
	mouseOverColor();
	
	//用户详情jsp提交表单
	$("#userSubmit").bind("click",function(){
		userSubmit();
	});
	
	page();
});
var userWindow={};

//用户列表鼠标悬浮行变色
function mouseOverColor(){
	$("#userList tr").mouseover(function(){
		$(this).find("td").addClass("mouse_color");
	}); 
	$("#userList tr").mouseout(function(){
		$(this).find("td").removeClass("mouse_color");
	}); 	
}

//通讯录列表鼠标悬浮行变色
function mouseOverAdd(){
	$("#addressList tr").mouseover(function(){
		$(this).find("td").addClass("mouse_color");
	}); 
}

//通讯录列表鼠标离开行变色
function mouseOutAdd(){
	$("#addressList tr").mouseout(function(){
		$(this).find("td").removeClass("mouse_color");
	}); 
}

//用户checkbox点击事件函数
function userAll_check_box_click(){
	var userCheckBox = $('input[name="userCheckBox"]');
	if($(this).attr("checked")){
		for(i in userCheckBox){
			$(userCheckBox[i]).attr("checked",true);
			var tr=$(userCheckBox[i]).parent().parent();
			$(tr).find("td").addClass("mouse_color");
		}
	}else{
		for(i in userCheckBox){
			$(userCheckBox[i]).attr("checked",false);
			var tr=$(userCheckBox[i]).parent().parent();
			$(tr).find("td").removeClass("mouse_color");
		}
	}
}

//通讯录checkbox点击事件函数
function address_check_box_click(){
	var obj = $('#addressAllBox');
	var addressCheckBox = $('input[name="addressCheckBox"]');  
	if($(obj).attr("checked")){
		for(i in addressCheckBox){
			$(addressCheckBox[i]).attr("checked",true);
		}
	}else{
		for(i in addressCheckBox){
			$(addressCheckBox[i]).attr("checked",false);
		}
	}
}

//删除用户
function deleteUser(){
		var userCheckBox = $('input[name="userCheckBox"]');
		var arr=[];
		for(i in userCheckBox){
			if(userCheckBox[i].checked){
				arr.push(userCheckBox[i].value);
			}
		}
		if(arr==0){
			alert("请选择所要删除的用户！");
			return; 
		}
		if(confirm("确认删除用户！")){
			var userIds="";
			for(var i in arr){
				userIds+=arr[i];
				if(i!=arr.length-1){
					userIds+=",";
				}
			}
			$.ajax({
				   type: "get",
				   url: "deleteUser",
				   data: "userIds="+userIds,
				   success: function(msg){
					   $.messager.show(0, "删除用户成功",1500);
					   setTimeout(function(){
						   window.location.href="http://localhost:8080/Address/userList";
					   }, 2500);
					   
					 }
				   
				});
		}
	
};


//添加用户 type=1  修改用户type=0
function updateUser(type){
//	alert(3);
	var userId =0;
	if(type==0){
		var userCheckBox = $('input[name="userCheckBox"]');
		var arr=[];
		for(i in userCheckBox){
			if(userCheckBox[i].checked){
				arr.push(userCheckBox[i].value);
			}
		}
		if(arr==0){
			alert("请选择所要修改的用户！");
			return; 
		}
		if(arr.length>1){
			alert("请选择一个用户进行修改！");
			return; 
		}
		userId = arr[0];
	}
	window.userWindow=window.open("http://127.0.0.1:8080/Address/userDetail.jsp?type="+type+"&userId="+userId, "newwindow", "height=300, width=500,top=100,left=500, toolbar =no, menubar=no, scrollbars=no, resizable=no, location=no, status=no");
//	console.log(window.userWindow);
};

//通讯录列表
function addressList(obj){
	//$(this).children().eq(1).html())
	var tr=$(obj).parent().parent();
	var userCheckBox = tr.children().eq(0).children().eq(0);
	var userId = userCheckBox[0].value;
	var addressList = $("#addressList");
	$.ajax({
		type:'post',
		url:'address/addressList',
		data:"userId="+userId,
		dataType:'json',
		success:function(json){
			$("#addressList tr").empty();
			console.log(json);
			addressList.attr("border","2");
			addressList.append("<tr><td colspan='4' align='center'><b><span id='selectedUser'></span></b>通讯录</td></tr>"+
							   "<tr><th><input type='checkbox' id='addressAllBox' onclick='address_check_box_click(this)'></th>"+
							   "<th>姓名</th><th>电话</th><th>邮件</th></tr>");
//			alert(json[0].name+"-"+json[0].tel);
			for(var i in json){
				addressList.append("<tr><td align='center' idth='30px'><input type='checkbox'  name='addressCheckBox' value="+json[i].addressId+"></td>" +
								   "<td>"+json[i].name+"</td>" +
								   "<td>"+json[i].tel+"</td>" +
								   "<td>"+json[i].email+"</td></tr>");
			}
			$("#addressListDiv a").empty();
			$("#addressListDiv").append("<a href='javascript:void(0)' onclick='deleteUserAdd()'>删除</a>  "+
							   "<a href='javascript:void(0)' onclick='updateUserAdd(0,"+userId+")'>修改</a>  "+
							   "<a href='javascript:void(0)' onclick='updateUserAdd(1,"+userId+")'>增加</a>");
			
			var tr=$(obj).parent().parent();
			var td = tr.children().eq(1);
//			console.log(td[0].innerHTML);
			$("#selectedUser").html(td[0].innerHTML);
		}
	});
}

//通讯录删除用户
function deleteUserAdd(){
	var userCheckBox = $('input[name="addressCheckBox"]');
	var arr=[];
	var addressIds="";
	for(i in userCheckBox){
		if(userCheckBox[i].checked){
			arr.push(userCheckBox[i].value);
			addressIds+=userCheckBox[i].value+",";
		}
	}
	if(arr==0){
		alert("请选择所要删除的用户！");
		return; 
	}
	if(confirm("确认删除？")){
		$.ajax({
			   type: "get",
			   url: "address/deleteUser",
			   data: "addressIds="+addressIds.substring(0, addressIds.length-1),
			   dataType:"text",
			   success: function(msg){
				   $.messager.show(0, "删除用户成功",1500);
				   setTimeout(function(){
					   window.location.reload();
				   }, 2500);
			   }
			});
		
	}
	
}

//通讯录修改、增加用户    添加用户 type=1  修改用户type=0
function updateUserAdd(type,userId){
//	alert(type+"--"+userId);
	var addressId = 0;
	var url="";
	if(type==0){
		var userCheckBox = $('input[name="addressCheckBox"]');
		var arr=[];
		for(i in userCheckBox){
			if(userCheckBox[i].checked){
				arr.push(userCheckBox[i].value);
			}
		}
		if(arr==0){
			alert("请选择所要修改的用户！");
			return; 
		}
		if(arr.length>1){
			alert("请选择一个用户进行修改！");
			return; 
		}
		addressId = arr[0];
		url="http://localhost:8080/Address/userDetailAddress.jsp?type=1&userId="+userId+"&addressId="+addressId;
	    window.open(url,"_blank");
	}else{
		//"<a target='_blank' href='http://localhost:8080/Address/userDetailAddress.jsp?type=0&userId="+userId+"'>修改</a>  "+
		url="http://localhost:8080/Address/userDetailAddress.jsp?type=1&userId="+userId;
//		location.href = url;
		window.open(url,"_blank");
	}
}

//提交
function userSubmit(){
	var userName = $.trim($("#userName").val());
	if(userName.length==0){
		alert("用户名称不能为空！");
		return;
	}
	var userId = $("#userId").val();
	var userName =$("#userName").val();
	var cityList = $("#cityList").val();
	var data = "userId="+userId+"&userName="+userName+"&cityList="+cityList;
	if(confirm("确认提交？")){
		$.ajax({
		   type: 'POST',
		   url: 'updateUser',
		   data: data,
		   dataType: 'json',
		   success: function (msg) {
			  /* this.window.opener = null; 
			   window.close();  */
			   /*var o=window.opener.userWindow;
			   return;
			   window.opener.window.userWindow.close();
			   window.opener.location.href="http://localhost:8080/Address/userList"; */
			  /*$.messager.show(0, "提交成功",1500);
			   setTimeout(function(){
				   window.close();
				   window.opener.location.href="http://localhost:8080/Address/userList"; 
			   }, 2500);*/
		   }
		});

		window.opener.location.href="http://127.0.0.1:8080/Address/userList";
		window.opener.userWindow.close();
		/*if(confirm("继续添加用户？")){
			
		}*/
	}
}

//分页
function page(){
	var arr = $("#pageParams").val().split(",");
//	alert(arr);return;
	var sum = Math.ceil(arr[0]/5);
	$("#page").append("<b>第&nbsp;</b>");
	for(var i=1;i<=sum;i++){
		if(i==arr[1]){
			$("#page").append("<b>"+i+"</b>&nbsp;");
		}else{
			$("#page").append("<a href='userList?page="+i+"'>"+i+"</a>&nbsp;");
		}
	}
	$("#page").append("<b>页&nbsp;</b>&nbsp;");
	$("#page").append(" 共<b>"+sum+"</b>页<br/><br/>");
}

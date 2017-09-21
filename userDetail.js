$().ready(function(){
	var userId = $("#userId").val();
	cityList();
	if(userId && userId!=0){
		userDetail(userId);
	}
	
	/*$("#userSubmit").bind("click",function(){
		userSubmit();
	});*/
});

//修改用户时查看详情
function userDetail(userId){
	$.ajax({
		   type: "POST",
		   url: "userDetail",
		   data: "userId="+userId,
		   success: function(msg){
			   var arr = msg.split(",");
			   $("#userName").val(arr[1]);
			   var sel = document.getElementById('cityList');
//			   alert(sel.options.length);
			   for(var i=0;i<sel.options.length;i++)
			   {
			      var s = sel.options[i]; 
			      if(arr[2]===s.value.trim())
			      {
			        s.selected=true;
			      }
			   }
		   }
		});
}


//初始化城市列表绑定下拉框
function cityList(){
	 $.ajax({  
         url: "cityList",   
         type: "post",  
         dataType: "text",  
         success: function (str) { 
        	 var data = eval('('+str+')');
        	 var optionstring = "";  
             for (var i in data) {  
                 var jsonObj =data[i];  
                 optionstring += "<option value=\"" + jsonObj.id + "\" >" + jsonObj.name + "</option>";  
             }  
//             $("#cityList").html("<option value='-1'>--请选择--</option> "+optionstring);  
               $("#cityList").html(optionstring);  
         },  
         error: function (msg) {  
             alert("出错了！");  
         }  
     });            

}

//重置
function clearUser(){
	$("#userName").val('');
	$("#cityList").val(1);
}

//提交
/*function userSubmit(){
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
		   success: function (data) {
			   this.window.opener = null; 
			   window.close();  
			   window.close();
			   window.opener.location.href="http://localhost:8080/Address/userList"; 
			  $.messager.show(0, "提交成功",1500);
			   setTimeout(function(){
				   window.close();
				   window.opener.location.href="http://localhost:8080/Address/userList"; 
			   }, 2500);
		   }

		});
	}
}
*/
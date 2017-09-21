<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户详情</title>

<script type="text/javascript" src="jsPlugin/jquery-1.7.2.js"></script>
<script type="text/javascript" src="jsPlugin/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="jsPlugin/jquery.messager.js"></script>
<script type="text/javascript" src="userDetail.js"></script>
<script type="text/javascript" src="userList.js"></script>
</head>
<body>
<center>
<form id="userForm" method="post" action=""  >
<input type="hidden" id="userId" name="userId" value="<%= request.getParameter("userId") %>">
<table border="1" cellpadding="8" cellspacing="0" id="userTable" >
<tr><td colspan="2" align="center">
	 <%
	   String type = request.getParameter("type"); 
	   if("1".equals(type)){
	 %>
		新增用户
	<%}else{%>
		修改用户 
	<%	   
	   }
	%>

</td></tr>
<tr>
	<td>姓名</td>
	<td><input type="text" style='width:80px;' id="userName" name="userName"></td>
</tr>
<tr>
	<td>城市</td>
	<td>
		<select id="cityList"  style='width:80px;' name="cityList"></select>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
	<input type="reset" id="clearUser" value="重置" onclick="clearUser()">
	<button id="userSubmit">提交</button>
	<!-- <input type="submit" id="submitUser" value="提交"> -->
	</td>
</tr>
</table>
</form>
</center>

</body>
</html>
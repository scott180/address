<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.util.*,com.sudy.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>用户列表</title>
<script type="text/javascript" src="jsPlugin/jquery-1.7.2.js"></script>
<script type="text/javascript" src="jsPlugin/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="jsPlugin/jquery.messager.js"></script>
<script type="text/javascript" src="userList.js"></script>
<style type="text/css">
	#userListDiv{
	    position: absolute;
        background:#eee;
        width: 300px;
        top: 10px;
        left: 100px;
        height: 300px
     }
     
     #addressListDiv{
     	position: absolute;
        width: 400px;
     	top:10px;
     	right:100px
     }
     
     #hrefDiv{
     	position: absolute;
        width: 400px;
     	top:230px;
     	left:1px
     }
     
     .mouse_color{
		background-color: #aaa;
	} 
</style>
</head>
<body>
<div id="userListDiv">
<table border="1" cellpadding="5" cellspacing="0" id="userList" width="300px" >
<tr><td colspan="4" align="center">人员列表</td></tr>
<tr align="center">
	<th><input type="checkbox" id="userAllBox"></th>
	<th width="60px">姓名</th>
	<th>城市</th>
	<th>通讯录</th>
</tr>
<%
	List<User> list = (List<User>)session.getAttribute("userList"); 
	for(int i=0;i<list.size();i++){
		User user = list.get(i);
		%>
	<tr align="center">
		
		<td><input type="checkbox" name="userCheckBox" value="<%= user.getId()%>"/> </td>
	<%-- 	<td><%= user.getId()%></td> --%>
		<td><%= user.getName()%></td>
		<td><%= user.getCityName()%></td>
		<td><a href="javascript:void(0)" onclick="addressList(this)">通讯录</a></td>
	</tr>	
		
<%		
	}
%>
</table>
<br/>
<div id="hrefDiv">
<div id="page"></div>
<input id="pageParams" type="hidden" value="<%= session.getAttribute("pageParams") %>" /> 
<a href="javascript:void(0)" onclick="deleteUser()">删除</a>
<a href="javascript:void(0)" onclick="updateUser(0)">修改</a>
<a href="javascript:void(0)" onclick="updateUser(1)">增加</a>
</div>
</div>


<div id="addressListDiv">
	<table cellpadding="5" cellspacing="0" id="addressList" align="center" width="400px" onmouseover="mouseOverAdd()" onmouseout="mouseOutAdd()">
<!-- 	<tr><td colspan="4" align="center"><span id="selectUser"></span>通讯录</td></tr>
	<tr>
		<th><input type="checkbox"></th>
		<th>姓名</th>
		<th>电话</th>
		<th>邮件</th>
	</tr> -->
	</table>
</div>
</body>
</html>
package com.sudy.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sudy.model.User;
import com.sudy.util.DB;
/*
 * 用户列表
 */
public class UserList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String page = (String)req.getParameter("page");
		Integer rows = 5;
		if(page==null || "".equals(page)){
			page = "1";
		}else{
			Integer t = Integer.parseInt(page);
			page = t.toString();
		}
		String begin = String.valueOf(((Integer.parseInt(page)-1)*rows));
		String sql = "SELECT u.id userId,u.name name,c.name cityName FROM user u left join city c on u.cityid=c.id order by u.id desc limit "+begin+","+rows;
		Connection conn = DB.getConnection();
		Statement st = DB.getStatement(conn);
		ResultSet rs = DB.getExcuteQuery(st, sql);
		List<User> list = new ArrayList<User>();
		try {
			while(rs.next()){
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setCityName(rs.getString(3));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.getSession().setAttribute("userList", list);
		DB.close(rs);
		DB.close(st);
		
		String sql2 = "SELECT count(*) count FROM user";
		Statement st2 = DB.getStatement(conn);
		ResultSet rs2 = DB.getExcuteQuery(st2, sql2);
		try {
			while(rs2.next()){
				Integer count = rs2.getInt(1);
				req.getSession().setAttribute("pageParams", count+","+page);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DB.close(rs2);
		DB.close(st2);
		DB.close(conn);
		resp.sendRedirect("userList.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	  
	}

	
}

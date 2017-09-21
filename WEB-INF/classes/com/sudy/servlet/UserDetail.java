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

import com.sudy.model.City;
import com.sudy.util.DB;
import com.sudy.util.Util;
/*
 * 用户详情
 */
public class UserDetail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String userId = req.getParameter("userId");
		String sql = "SELECT id userId,name userName,cityid FROM user where id = "+userId;
		Connection conn = DB.getConnection();
		Statement st = DB.getStatement(conn);
		ResultSet rs = DB.getExcuteQuery(st, sql);
		String s ="";
		try {
			while(rs.next()){
				s+=rs.getInt(1)+",";
				s+=rs.getString(2)+",";
				s+=rs.getInt(3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(s);
		DB.close(rs);
		DB.close(st);
		DB.close(conn);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	  
	}

	
}

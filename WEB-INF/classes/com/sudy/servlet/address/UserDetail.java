package com.sudy.servlet.address;

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
 * 通讯录-用户详情
 */
public class UserDetail extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String addressId = req.getParameter("addressId");
		String sql = "SELECT id addressId,userid,name,tel,email FROM addresslist where id= "+addressId;
		Connection conn = DB.getConnection();
		Statement st = DB.getStatement(conn);
		ResultSet rs = DB.getExcuteQuery(st, sql);
		String s ="";
		try {
			while(rs.next()){
				s+=rs.getInt(1)+",";
				s+=rs.getInt(2)+",";
				s+=rs.getString(3)+",";
				s+=rs.getString(4)+",";
				s+=rs.getString(5);
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

package com.sudy.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
 * 删除用户
 */
public class DeleteUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String userIds = req.getParameter("userIds");
		String msg = "0";
		Connection conn = DB.getConnection();
		
		if(Util.isNotEmpty(userIds)){
			try {
				String sql="DELETE from user where id in("+userIds+")";
				System.out.println(sql);
				PreparedStatement ps = DB.getPreparedStatement(conn, sql);
				ps.executeUpdate();
				DB.close(ps);
				
				String sql2="DELETE from addresslist where userid in("+userIds+")";
				System.out.println(sql2);
				PreparedStatement ps2 = DB.getPreparedStatement(conn, sql2);
				ps2.executeUpdate();
				DB.close(ps2);
				msg="1";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DB.close(conn);
		resp.getWriter().write(msg);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	  
	}

	
}

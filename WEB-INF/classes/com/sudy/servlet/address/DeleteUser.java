package com.sudy.servlet.address;

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
 * 通讯录-删除用户
 */
public class DeleteUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String addressIds = req.getParameter("addressIds");
		String msg = "0";
		Connection conn = DB.getConnection();
		
		if(Util.isNotEmpty(addressIds)){
			String sql="DELETE from addresslist where id in("+addressIds+")";
			System.out.println(sql);
			PreparedStatement ps = DB.getPreparedStatement(conn, sql);
			try {
				ps.executeUpdate();
				msg="1";
				DB.close(ps);
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

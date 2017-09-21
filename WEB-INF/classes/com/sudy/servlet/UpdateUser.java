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
 * 增加、修改用户
 */
public class UpdateUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String userName = req.getParameter("userName");
		String cityId = req.getParameter("cityList");
		String userId = req.getParameter("userId");
	
		//insert into user values(null,"赵六","2");
		String sql = "";
		Connection conn = DB.getConnection();
		
		if(Util.isNotEmpty(userName) && Util.isNotEmpty(cityId)){
			if(Util.isNotEmpty(userId) && !"0".equals(userId)){//修改用户
				sql = "update user set name=\""+userName+"\",cityid="+cityId+" where id = "+userId;
			}else{//新增用户
				sql = "insert into user values(null,\""+userName+"\","+cityId+")";
			}
			//System.out.println(sql);
			PreparedStatement ps = DB.getPreparedStatement(conn, sql);
			try {
				ps.executeUpdate();
				DB.close(ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Util.outError(resp);
			}
		}
		DB.close(conn);
		Util.outSuccess(resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	  
	}

	
}

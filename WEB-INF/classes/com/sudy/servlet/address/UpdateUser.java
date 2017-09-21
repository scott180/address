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
 * 通讯录-增加、修改用户
 */
public class UpdateUser extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String userId = req.getParameter("userId");
		String name = req.getParameter("name");
		String tel = req.getParameter("tel");
		String email = req.getParameter("email");
		String addressId = req.getParameter("addressId");
	
		//insert into user values(null,"赵六","2");
		String sql = "";
		Connection conn = DB.getConnection();
		
		if(Util.isNotEmpty(name) && Util.isNotEmpty(tel) && Util.isNotEmpty(email)){
			if(Util.isNotEmpty(addressId) && !"0".equals(userId)){//修改用户
				sql = "update addresslist set name=\""+name+"\",tel=\""+tel+"\",email=\""+email+"\" where id = "+addressId;
			}else{//新增用户
				//insert into addresslist values(null,1,'11','120','22@162.com'); 
				sql = "insert into addresslist values(null,"+userId+",\""+name+"\",\""+tel+"\",\""+email+"\")";
			}
			System.out.println(sql);//insert into user values(null,"1",测试",15211111122",1@qq.com)
			PreparedStatement ps = DB.getPreparedStatement(conn, sql);
			try {
				ps.executeUpdate();
				DB.close(ps);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Util.outSuccess(resp);
			}
		}
		DB.close(conn);
//		resp.getWriter().write("1");
		Util.outSuccess(resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	  
	}

	
}

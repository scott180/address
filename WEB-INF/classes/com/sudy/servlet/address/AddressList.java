package com.sudy.servlet.address;

import java.io.IOException;
import java.io.PrintWriter;
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

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.sudy.model.User;
import com.sudy.util.DB;

/*
 * 通讯录-用户列表
 */
public class AddressList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userId = req.getParameter("userId");
		String page = (String)req.getParameter("page");
		if(page==null || "".equals(page)){
			page = "0";
		}else{
			Integer t = Integer.parseInt(page)-1;
			page = t.toString();
		}
		String sql = "SELECT id,userid,name,tel,email FROM addresslist where userid="+userId+" limit "+page+",100";
		System.out.println(sql);
		Connection conn = DB.getConnection();
		Statement st = DB.getStatement(conn);
		ResultSet rs = DB.getExcuteQuery(st, sql);
		List<User> list = new ArrayList<User>();
		try {
			while(rs.next()){
				User user = new User();
				user.setAddressId(rs.getInt(1));
				user.setId(rs.getInt(2));
				user.setName(rs.getString(3));
				user.setTel(rs.getString(4));
				user.setEmail(rs.getString(5));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray json = JSONArray.fromObject(list);
		resp.setCharacterEncoding("UTF-8");  
	    resp.setContentType("application/json; charset=utf-8"); 
		PrintWriter out = resp.getWriter();
		out.append(json.toString());
		
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

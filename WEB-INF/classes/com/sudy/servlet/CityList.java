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
 * 城市列表
 */
public class CityList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String sql = "select id,name from city";
		Connection conn = DB.getConnection();
		Statement st = DB.getStatement(conn);
		ResultSet rs = DB.getExcuteQuery(st, sql);
		List<City> list = new ArrayList<City>();
		try {
			while(rs.next()){
				City city = new City();
				city.setId(rs.getInt(1));
				city.setName(rs.getString(2));
				list.add(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json = Util.list2Json(list);
		resp.setCharacterEncoding("UTF-8");
		
		resp.getWriter().write(json);
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

package com.sudy.util;

/*
 * jdbc杩炴帴mysql鏁版嵁搴�
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	public static void main(String[] args) {
		Connection conn = getConnection();
		System.out.println(conn.toString());
		try {
			//String sql = "insert into user values(null,'鐜嬩簲1',1)";
			//PreparedStatement ps = getPreparedStatement(conn, sql);
			//System.out.println(ps.executeUpdate());
			
			Statement st = getStatement(conn);
			String sql = "SELECT u.id userId,u.name name,c.name cityName FROM user u left join city c on u.cityid=c.id";
			ResultSet rs = getExcuteQuery(st, sql);
			while(rs.next()){
				System.out.println(rs.getInt(1)+"-"+rs.getString(2)+"-"+rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost/yqxu?user=root&password=12344&useSSL=false";
			conn=DriverManager.getConnection(url);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("娌℃湁mysql椹卞姩");
		}
		return conn;
	}
	
	public static Statement getStatement(Connection conn){
		Statement st=null;
		try {
			st=conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return st;
	}
	
	public static PreparedStatement getPreparedStatement(Connection conn,String sql){
		PreparedStatement prst=null;
		try {
			prst=conn.prepareStatement(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prst;
	}
	
	public static ResultSet getExcuteQuery(Statement st,String sql){
		ResultSet rs=null;
		try {
			rs=st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void close(Connection conn){
		if(conn!=null){
		try {
			conn.close();
			conn=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	public static void close(Statement st){
		if(st!=null){
			try {
				st.close();
				st=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
				rs=null;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

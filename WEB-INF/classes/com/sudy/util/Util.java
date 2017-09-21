package com.sudy.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.sudy.model.City;
/*
 * 工具类
 */
public class Util {
	
	public static void main(String[] args) {
	/*	List<City> list=new ArrayList<City>();
		City city=new City();
		city.setId(1);
		city.setName("合肥");
		list.add(city);
		City city1=new City();
		city1.setId(2);
		city1.setName("芜湖");
		list.add(city1);
		System.out.println(list2Json(list));*/
		String s="null";
		System.out.println(isNotEmpty(s));
		
	}
	
	//城市列表转换json
	public static String list2Json(List<City> list){
		StringBuffer sb =  new StringBuffer();
		sb.append("[");
		for(int i=0 ;i<list.size();i++){
			City city = list.get(i);
			sb.append("{");
			sb.append("\"id\":\""+city.getId()+"\",");
			sb.append("\"name\":\""+city.getName());
			sb.append("\"}");
			if(i!=list.size()-1){
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	
	//字符串非空判断
	public static boolean isNotEmpty(String s){
		if(s==null || "".equals(s) || "null".equals(s)){
			return false;
		}
		return true;
	}

	//json传递成功消息
	public static void outSuccess(HttpServletResponse resp) {
		// TODO Auto-generated method stub
		out(resp,1);
	}
	
	//json传递失败消息
	public static void outError(HttpServletResponse resp) {
		// TODO Auto-generated method stub
		out(resp,0);
	}

	public static void out(HttpServletResponse resp,Integer type){
		resp.setCharacterEncoding("UTF-8"); 
		resp.setContentType("application/json; charset=utf-8");
//		String msg = "[{\"msg\":1}]";
		Map<String,Integer> msg = new HashMap<String,Integer>();
		msg.put("msg", type);
		JSONArray json = JSONArray.fromObject(msg);
		try {
			resp.getWriter().append(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
